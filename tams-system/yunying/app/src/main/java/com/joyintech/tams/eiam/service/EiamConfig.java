package com.joyintech.tams.eiam.service;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Component
public class EiamConfig implements InitializingBean {

    /**
     * 应急模式标志位
     */
    public static final String EMERGENCY_MODE = "1";
    /**
     * 正常模式标志位
     */
    public static final String NORMAL_MODE = "0";
    /**
     * 系统代码
     */
    public static final String SYSTEM_CODE = "EIAM";
    /**
     * 参数代码
     */
    public static final String PARA_CODE = "STRINGENT_STATE";

    private boolean enable;
    private String appCode;
    private String appKey;
    private Integer httpConnectTimeout;
    private Integer httpReadTimeout;
    private List<String> hosts;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.init();
    }

    private void init() throws Exception {
        final String resourceName = "eiam_client_config.properties";

        Properties prop = new Properties();
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(resourceName)) {
            prop.load(is);
        }

        this.enable = BooleanUtils.toBooleanObject(prop.getProperty("enableCaspClient"));
        this.appCode = prop.getProperty("appCode");
        this.appKey = prop.getProperty("appKey");
        this.hosts = Collections.unmodifiableList(Arrays.asList(StringUtils.split(prop.getProperty("hosts"), ",")));
        this.httpConnectTimeout = Integer.parseInt(prop.getProperty("httpConnectTimeout"));
        this.httpReadTimeout = Integer.parseInt(prop.getProperty("httpReadTimeout"));
    }

    public boolean enable() {
        return enable;
    }

    public String getAppCode() {
        return appCode;
    }

    public String getAppKey() {
        return appKey;
    }

    public List<String> getHosts() {
        return hosts;
    }

    public Integer getHttpConnectTimeout() {
        return httpConnectTimeout;
    }

    public Integer getHttpReadTimeout() {
        return httpReadTimeout;
    }

}
