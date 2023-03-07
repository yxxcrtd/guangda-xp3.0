/*
 * 文件名：SSOPermissionsAuthorizationFilter.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改时间：2017年5月19日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.framework.sso;

import com.joyintech.framework.config.ShiroProperties;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.jwt.JWTToken;
import com.joyintech.framework.jwt.JWTUtil;
import com.joyintech.framework.security.AuthorizationService;
import com.joyintech.framework.security.JoyinFormAuthenticationFilter;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.security.session.dao.SessionDaoMapper;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * SSO权限过滤器
 *
 * @version 1.0 2017年5月19日
 * @see SSOPermissionsAuthorizationFilter
 * @since 1.0
 * @since 1.0.1 获取sso用户名增加HttpServletResponse参数
 */
public class SSOPermissionsAuthorizationFilter extends JoyinFormAuthenticationFilter {

    public SSOPermissionsAuthorizationFilter(SessionDaoMapper sessionDaoMapper, ShiroProperties shiroProperties) {
        super(sessionDaoMapper, shiroProperties);
    }

    /**
     *
     */
    private Logger logger = LoggerFactory.getLogger(SSOPermissionsAuthorizationFilter.class);

    /**
     * 从Cookie值里取出SSOUSERNAME的处理接口
     */
    private SSOCookieOperateor ssoCookieOpr;

    /**
     * UserService
     */
    private AuthorizationService userService;

    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        return super.executeLogin(request, response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        String ssoUserName = getSsoUserName(WebUtils.toHttp(request), WebUtils.toHttp(response));
        if (StringUtil.isNotEmpty(ssoUserName) && !SecurityUtil.hasAuthenticated()) {
            if (logger.isDebugEnabled()) {
                logger.debug("根据SSO用户名[" + ssoUserName + "]进行登录认证.");
            }
            try {
                // 通过认证后的SSO用户名获取用户信息，并设置到session中
                User user = userService.getUserInfoBySSOName(ssoUserName);
                if (null != user) {
                    boolean rememberMe = isRememberMe(request);
                    String host = getHost(request);
                    AuthenticationToken token = new SSOUsernameToken(user.getUserName(), user.getPassword(), rememberMe, host);
                    Subject subject = getSubject(request, response);
                    subject.login(token);
                    if (logger.isDebugEnabled()) {
                        logger.debug("通过SSO用户名[" + ssoUserName + "]成功登录系统.");
                        logger.debug("进入系统第一个请求的地址：" + WebUtils.getRequestUri(WebUtils.toHttp(request)));
                    }
                    return onLoginSuccess(token, subject, request, response);
                }
                else {
                    throw new AccountNotFoundException("系统中不存在SSO用户名为 [" + ssoUserName + "]的用户。");
                }
            }
            catch (Exception e) {
                logger.error("sso异常", e);
                return false;
            }
        }
        return super.onAccessDenied(request, response);
    }

    /**
     * 获取SSO认证成功后的用户名
     * @param httpRequest HttpServletRequest
     * @return SSO用户名
     */
    protected String getSsoUserName(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        HttpSession session = httpRequest.getSession(false);
        String ssoUserName = StringUtil.EMPTY;
        if (ssoCookieOpr != null) {
            return ssoCookieOpr.getSsoUserName(httpRequest, httpResponse);
        }
        // 从Session中取得ssoUserName
        if (null != session) {
            ssoUserName = (String)session.getAttribute(
                    CacheUtil.getSysParam(GlobalConstant.SSOUSERNAME_KEY));
            if (logger.isDebugEnabled()) {
                logger.debug("从session中获取的用户名：" + ssoUserName);
            }
        }
        // SSO认证后应该会带会该属性
        if (StringUtil.isEmpty(ssoUserName)) {
            ssoUserName = getSsoUsrFromCookieVal(httpRequest, CacheUtil.getSysParam(GlobalConstant.SSOUSERNAME_KEY));
            if (logger.isDebugEnabled()) {
                logger.debug("从Cookie中获取的用户名：" + ssoUserName);
            }
        }
        return ssoUserName;
    }

    /**
     * 从Cookie中取得SSOUserName
     *
     * @param httpRequest request请求
     * @param cookieName cookie名称
     * @return SSOUserName
     * @see
     * @since 1.0
     */
    private String getSsoUsrFromCookieVal(HttpServletRequest httpRequest, String cookieName) {
        String ssoUserName = StringUtil.EMPTY;
        Cookie[] cookies = httpRequest.getCookies();
        if (cookies == null) {
            return ssoUserName;
        }
        if (cookieName == null) {
            ssoUserName = cookies[0].getValue();
            logger.debug("未配置系统参数[ssoCookieName]");
        }
        else {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    ssoUserName = cookie.getValue();
                    break;
                }
            }
        }
        return ssoUserName;
    }


    /**
     * 获取 ssoCookieOpr
     * @return ssoCookieOpr.
     */
    public SSOCookieOperateor getSsoCookieOpr() {
        return ssoCookieOpr;
    }


    /**
     * 设置 ssoCookieOpr
     * @param ssoCookieOpr ssoCookieOpr
     */
    public void setSsoCookieOpr(SSOCookieOperateor ssoCookieOpr) {
        this.ssoCookieOpr = ssoCookieOpr;
    }


    /**
     * 获取 userService
     * @return userService.
     */
    public AuthorizationService getUserService() {
        return userService;
    }


    /**
     * 设置 userService
     * @param userService userService
     */
    public void setUserService(AuthorizationService userService) {
        this.userService = userService;
    }

}
