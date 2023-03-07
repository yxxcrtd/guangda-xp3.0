package com.joyintech.tams.eiam.service.checker;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
import com.jh.iam.utils.crypto.sm.sm4.SM4Utils;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.system.user.dao.UserMapper;
import com.joyintech.tams.eiam.service.EiamConfig;
import com.joyintech.tams.eiam.service.EiamUserInfoSyncService;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class EiamUserTokenChecker implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(EiamUserTokenChecker.class);

    @Autowired
    private EiamConfig config;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EiamUserInfoSyncService eiamUserInfoSyncService;

    private RestTemplate restTemplate;
    private IRule rule;

    @Override
    public void afterPropertiesSet() throws Exception {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        // 请求连接超时时间
        factory.setConnectTimeout(config.getHttpConnectTimeout());
        // 获取数据超时时间
        factory.setReadTimeout(config.getHttpReadTimeout());
        restTemplate = new RestTemplate(factory);

        // 使用LoadBalance来获取服务器地址
        BaseLoadBalancer loadBalancer = new BaseLoadBalancer();
        loadBalancer.setServersList(config.getHosts());
        rule = new RandomRule();
        rule.setLoadBalancer(loadBalancer);
    }

    public String check(String token, HttpServletRequest request) {
        try {
            return this.doCheck(token);
        } catch (Exception e) {
            logger.error("EIAM认证失败", e);
            return null;
        }
    }

    @Transactional
    public String doCheck(String token) {
        logger.info("token:{}", token);
        // 获取一个服务器
        String serverUrl = String.format("http://%s/newcasp/api/sso/authenticate", this.getUrl());

        // 参数
        Map<String, Object> map = Maps.newHashMap();
        map.put("appcode", config.getAppCode());
        map.put("iamtoken", this.encrypt(token));
        // header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // request
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(map, headers);

        // 执行
        logger.info("url:{}", serverUrl);
        logger.info("params:{}", map);
        String result = restTemplate.postForObject(serverUrl, request, String.class);
        logger.info("result: {}", result);

        // 判断返回结果
        EiamResponse response = JsonUtils.json2Object(result, EiamResponse.class);
        if (!response.success()) {
            logger.warn("eiam调用失败 {}", response);
            return null;
        }

        // 添加用户
        String username = response.getUsername();
        String realName = response.getRealName();
        eiamUserInfoSyncService.addUser(username, realName);

        return username;
    }

    public String encrypt(String value) {
        return SM4Utils.sm4(value, config.getAppKey());
    }

    private String getUrl() {
        return rule.choose(null).getHostPort();
    }

    public static class EiamResponse {
        private String retCode;
        @JsonProperty(value = "empNo")
        private String username;
        @JsonProperty(value = "name")
        private String realName;

        public void setRetCode(String retCode) {
            this.retCode = retCode;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public boolean success() {
            return StringUtils.equals(retCode, "1000");
        }

    }

}
