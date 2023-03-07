package com.joyintech.tams.eiam.service.checker;

import com.joyintech.framework.cache.CacheKey;
import com.joyintech.framework.security.RetryLimitAuthService;
import com.joyintech.framework.security.User;
import com.joyintech.framework.security.UserCredentialsService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.system.base.dao.SysUserMapper;
import com.joyintech.system.user.service.UserService;
import com.joyintech.tams.eiam.service.EiamSSOCookieOperator;
import com.ultrapower.ams.util.EncryptUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class EiamUserPasswordChecker implements RetryLimitAuthService, InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(EiamUserPasswordChecker.class);

    @Autowired
    private UserService userService;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private UserCredentialsService userCredentialsService;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private EiamSSOCookieOperator eiamSSOCookieOperator;

    private Cache<String, AtomicInteger> passwordRetryCache;
    private Cache<String, String> userLockCache;

    @Override
    public void afterPropertiesSet() throws Exception {
        passwordRetryCache = cacheManager.getCache(CacheKey.PASSWORD_RETRY_CACHE);
        userLockCache = cacheManager.getCache(CacheKey.USER_LOCK_CACHE);
    }

    @Override
    public boolean checkUserAndPassword(String userName, String password) {

        boolean success = this.doCheck(userName, password);
        if (success) {
            passwordRetryCache.remove(userName);
            userLockCache.remove(userName);
        } else {
            AtomicInteger retryCount = passwordRetryCache.get(userName);
            if (retryCount == null) {
                retryCount = new AtomicInteger(0);
                passwordRetryCache.put(userName, retryCount);
            }
            retryCount.incrementAndGet();

            // 上次锁定时间
            String lastLockTime = userLockCache.get(userName);
            if (lastLockTime == null) {
                userLockCache.put(userName, DateUtil.format(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
            }

        }

        return success;
    }

    public boolean doCheck(String userName, String password) {
        User user = userService.getUser(userName);
        if (user == null) {
            return false;
        }

        // EIAM认证
        boolean success = this.eiamCheck(user, password);
        if (success) {
            return true;
        }
        // 本系统认证
        success = this.systemCheck(user, password);
        if (success) {
            return true;
        }

        return false;
    }

    public boolean eiamCheck(User user, String password) {
        try {
            return EncryptUtil.verifyPwd(user.getPassword(), password);
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }

    public boolean systemCheck(User user, String password) {
        // 使用明文密码获取密文密码
        String encryptPassword = userCredentialsService.encryptCredentials(password, user.getSalt());

        logger.debug("database password: {}, encrypt password: {}", user.getPassword(), encryptPassword);

        // 验证两次hash的密码是否相同
        return StringUtils.equals(user.getPassword(), encryptPassword);
    }

}
