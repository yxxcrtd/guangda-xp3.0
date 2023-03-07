package com.joyintech.tams.eiam.config;

import com.joyintech.framework.sso.SSOCookieOperateor;
import com.joyintech.tams.eiam.service.EiamSSOCookieOperator;
import com.joyintech.tams.eiam.service.checker.EiamUserPasswordChecker;
import com.joyintech.tams.eiam.service.checker.EiamUserTokenChecker;
import com.joyintech.tams.eiam.ws.service.OperatorService;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.xml.ws.Endpoint;

@Configuration
@ConditionalOnProperty(name = "application.joyinsso", havingValue = "true")
public class EiamConfigure {

    @Primary
    @Bean
    public SSOCookieOperateor ssoCookieOperateor() {
        return new EiamSSOCookieOperator();
    }

    @Primary
    @Bean
    public EiamUserTokenChecker userTokenChecker() {
        return new EiamUserTokenChecker();
    }

    @Primary
    @Bean
    public EiamUserPasswordChecker userPasswordChecker() {
        return new EiamUserPasswordChecker();
    }

    // 消息总线
    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    // 添加webservice访问的前缀路径
    @Bean
    public ServletRegistrationBean webService() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new CXFServlet(), "/webService/*");
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }

    @Bean
    public Endpoint endpoint(OperatorService operatorService) {
        EndpointImpl endpoint = new EndpointImpl(springBus(), operatorService);
        endpoint.publish("/eiam/operatorService");
        return endpoint;
    }

}