package com.joyintech.tams.eiam.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.system.base.dao.SysUserAddMapper;
import com.joyintech.system.base.dao.SysUserMapper;
import com.joyintech.system.base.entity.SysUser;
import com.joyintech.system.base.entity.SysUserAdd;
import com.joyintech.system.constant.SystemDictCodeList;
import com.joyintech.system.user.SysUserListener;
import com.joyintech.system.user.dao.UserMapper;
import com.joyintech.system.user.dto.User;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

@Service
public class EiamUserPasswordSyncService {

    private static final Logger logger = LoggerFactory.getLogger(EiamUserPasswordSyncService.class);

    @Value("${eiam.properties.filepath}")
    private String filepath;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserAddMapper sysUserAddMapper;
    @Autowired(required = false)
    private SysUserListener sysUserListener;

    @Autowired
    private EiamUserPasswordSyncService service;

    public boolean sync(File file) {
        if (!file.exists() || !file.isFile()) {
            return false;
        }

        int successCount = 0;
        int failCount = 0;
        try {
            List<String> lines = FileUtils.readLines(file, Charset.forName("UTF-8"));
            int index = -1;
            String line = null;
            String username = null;
            String shaPassword = null;
            boolean success = false;
            for (int i = 0; i < lines.size(); i++) {
                line = lines.get(i);

                // invalid data
                if (line.length() == 0) {
                    continue;
                }

                index = line.indexOf(",");
                if (index == -1) {
                    logger.warn("invalid data in {} with {}", i + 1, line);
                    continue;
                }
                username = line.substring(0, index);
                shaPassword = line.substring(index + 1);

                // 执行更新
                success = this.update(username, shaPassword);
                if (success) {
                    successCount++;
                } else {
                    failCount++;
                }
            }
            return true;
        } catch (Exception e) {
            logger.error("", e);
            return false;
        } finally {
            logger.info("success: {}, fail: {}", successCount, failCount);
        }

    }

    public boolean update(String username, String shaPassword) {
        try {
            return this.doUpdate(username, shaPassword);
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean doUpdate(String username, String shaPassword) {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            return false;
        }
        String userId = user.getUid();

        // 系统用户
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setPassword(shaPassword);
        sysUser.setSystemCode(EiamConfig.SYSTEM_CODE);
        sysUser.setIsFrozen(SystemDictCodeList.是否_否.getValue());
        sysUser.setLastPassDate(new Date());
        sysUserMapper.updateByPrimaryKeySelective(sysUser);

        // 设置密码重置时间
        SysUserAdd sysUserAdd = new SysUserAdd();
        sysUserAdd.setUserId(userId);
        sysUserAdd.setPwdResetDate(DateUtil.parse(DateUtil.getSysDate()));
        sysUserAdd.setLstLoginTime(DateUtil.parse(DateUtil.getSysDate()));
        sysUserAddMapper.updateByPrimaryKeySelective(sysUserAdd);

        // 异步调用监听
        if (sysUserListener != null) {
            sysUserListener.passwordReset(userId);
        }

        return true;
    }


}
