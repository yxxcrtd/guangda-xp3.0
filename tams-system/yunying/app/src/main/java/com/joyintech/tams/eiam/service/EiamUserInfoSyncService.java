package com.joyintech.tams.eiam.service;

import com.joyintech.framework.security.User;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.system.actorassign.dao.SystemActorAssign002Mapper;
import com.joyintech.system.base.dao.SysUserAddMapper;
import com.joyintech.system.base.dao.SysUserMapper;
import com.joyintech.system.base.entity.SysUser;
import com.joyintech.system.base.entity.SysUserAdd;
import com.joyintech.system.constant.SystemDictCodeList;
import com.joyintech.system.user.dao.UserMapper;
import com.joyintech.workflow.engine.constant.WorkFlowValueType;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(readOnly = true)
public class EiamUserInfoSyncService {

    private static final Logger logger = LoggerFactory.getLogger(EiamUserInfoSyncService.class);

    private static final RandomNumberGenerator RANDOM_NUMBER_GENERATOR = new SecureRandomNumberGenerator();

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SystemActorAssign002Mapper sysActorAssign002Mapper;
    @Autowired
    private SysUserAddMapper sysUserAddMapper;

    public User findUser(String username) {
        logger.info("======查询用户,UserName[{}]======", username);
        return userMapper.findByUsername(username);
    }

    @Transactional
    public boolean addUser(String username, String realName) {
        logger.info("======添加用户,UserName[{}]======", username);
        User user = userMapper.findByUsername(username);

        if (user == null) {
            // 添加
            this.doAddSysUser(username, realName);
        } else {
            // 修改
            this.doModifySysUser(user.getUid(), username, realName);
        }

        return true;
    }

    @Transactional
    public boolean delUser(String username) {
        logger.info("======删除用户,UserName[{}]======", username);
        User user = userMapper.findByUsername(username);
        if (user == null) {
            return false;
        }

        // 删除用户
        sysUserMapper.deleteByPrimaryKey(user.getUid());
        // 删除用户角色
        sysActorAssign002Mapper.deleteRoleAssignDEByUserId(user.getUserName());
        // 删除用户主页配置和主页配置表
        userMapper.deleteHomePageConfig(user.getUserName());
        userMapper.deleteUserHomePageConfig(user.getUserName());
        // 删除用户样式
        userMapper.deleteUserStyle(user.getUserName());
        // 删除待办人
        userMapper.deleteWflAgent(user.getUserName());
        // 删除审批人
        userMapper.deleteUserWflNode(user.getUserName(), WorkFlowValueType.用户.getCode());
        // 删除流程模版中的默认审批人
        userMapper.deleteDefaultWflUser(user.getUserName());

        return true;
    }

    @Transactional
    public boolean modifyUserInfo(String username, String realName) {
        logger.info("======修改用户信息,UserName[{}]======", username);
        User user = userMapper.findByUsername(username);

        if (user == null) {
            this.doAddSysUser(username, realName);
        } else {
            this.doModifySysUser(user.getUid(), username, realName);
        }

        return true;
    }

    @Transactional
    public void doAddSysUser(String userName, String realName) {
        logger.info("======新增用户,UserName[{}]======", userName);
        SysUser sysUser = new SysUser();
        sysUser.setUserId(IDGenerator.generate());
        sysUser.setUserName(userName);
        sysUser.setSsoUserId(userName);
        sysUser.setRealName(realName);
        sysUser.setPassword("{SHA}");
        sysUser.setSalt(RANDOM_NUMBER_GENERATOR.nextBytes().toHex());
        sysUser.setSystemCode(EiamConfig.SYSTEM_CODE);
        sysUser.setCreateUser("system");
        sysUser.setUpdateUser("system");
        sysUser.setCreateTime(new Date());
        sysUser.setUpdateTime(new Date());
        sysUser.setIsFrozen(SystemDictCodeList.是否_否.getValue());
        sysUser.setLastPassDate(new Date());
        sysUser.setRemark(EiamConfig.SYSTEM_CODE);
        sysUserMapper.insert(sysUser);
        // 新增用户附属表
        SysUserAdd sysUserAdd = new SysUserAdd();
        sysUserAdd.setUserId(sysUser.getUserId());
        sysUserAdd.setLstLoginTime(DateUtil.parse(DateUtil.getSysDate()));
        sysUserAddMapper.insert(sysUserAdd);

    }

    @Transactional
    public boolean doModifySysUser(String userId, String username, String realName) {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setUserName(username);
        sysUser.setRealName(realName);
        sysUser.setSsoUserId(username);
        sysUser.setSystemCode(EiamConfig.SYSTEM_CODE);
        sysUser.setIsFrozen(SystemDictCodeList.是否_否.getValue());
        sysUser.setLastPassDate(new Date());
        sysUser.setUpdateTime(new Date());
        sysUser.setUpdateUser("system");
        sysUser.setRemark(EiamConfig.SYSTEM_CODE);
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        // 更新用户附属表
        SysUserAdd sysUserAdd = sysUserAddMapper.selectByPrimaryKey(userId);
        if (sysUserAdd != null) {
            sysUserAddMapper.updateByPrimaryKeySelective(sysUserAdd);
        }

        return true;
    }

}
