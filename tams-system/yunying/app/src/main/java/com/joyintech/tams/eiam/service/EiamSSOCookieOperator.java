package com.joyintech.tams.eiam.service;

import com.joyintech.framework.sso.SSOCookieOperateor;
import com.joyintech.tams.eiam.dao.OperatorMapper;
import com.joyintech.tams.eiam.service.checker.EiamUserTokenChecker;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EiamSSOCookieOperator implements SSOCookieOperateor {

    private static final Logger logger = LoggerFactory.getLogger(EiamSSOCookieOperator.class);

    @Value(value = "${server.context-path}/auth/login")
    private String loginUrl;

    @Autowired
    private OperatorMapper operatorMapper;

    @Autowired
    private EiamConfig config;
    @Autowired
    private EiamUserTokenChecker userTokenChecker;

    @Autowired
    private EiamUserInfoSyncService eiamUserInfoSyncService;

    @Override
    public String getSsoUserName(HttpServletRequest request) {
        return null;
    }

    @Override
    public String getSsoUserName(HttpServletRequest request, HttpServletResponse response) {

        // 只处理登录操作
        String requestURI = request.getRequestURI();
        logger.debug("requestURI:{}", requestURI);
        if (!StringUtils.equals(requestURI, loginUrl)) {
            return null;
        }


        String method = request.getMethod(); // GET/POST
        logger.debug("method:{}", method);

        // EIAM单点登录
        String token = request.getParameter("iamtoken");
        if (StringUtils.equals(method, "GET") && StringUtils.isNotBlank(token)) {

            // 调用远程
            String username = userTokenChecker.check(token, request);
            if (StringUtils.isNotBlank(username)) {
                return username;
            }

            this.direct2Denied(request, response);
            return null;
        }

        // 非应急模式
        boolean emergency = this.isEmergency(request);
        if (!emergency) {
            this.direct2Denied(request, response);
            return null;
        }

        return null;
    }

    private boolean isEmergency(HttpServletRequest request) {

        // 配置是否可用
        if (!config.enable()) {
            logger.warn("eiam current disable.");
            return false;
        }

        // 数据库是否开启应急模式
        String sysParamValue = operatorMapper.getSysParaValue(EiamConfig.SYSTEM_CODE, EiamConfig.PARA_CODE);
        if (!StringUtils.equals(EiamConfig.EMERGENCY_MODE, sysParamValue)) {
            return false;
        }

        return true;
    }

    private void direct2Denied(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/eiam/login/denied");

            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.error("", e);
        }
    }

}