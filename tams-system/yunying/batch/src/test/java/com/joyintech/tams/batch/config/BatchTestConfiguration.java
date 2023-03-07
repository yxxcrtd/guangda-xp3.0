package com.joyintech.tams.batch.config;

import com.joyintech.batch.core.BatchApplicationListener;
import com.joyintech.batch.core.JoyinBatchMainPolling;
import com.joyintech.batch.core.JoyinBatchQuartzPolling;
import com.joyintech.batch.core.event.ServerEvent;
import com.joyintech.framework.cache.ICacheService;
import com.joyintech.tams.batch.local.LocalInterfaceFileLoader;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.ContextRefreshedEvent;

import java.io.File;
import java.util.Comparator;
import java.util.Map;

@Configuration
public class BatchTestConfiguration {

    @Bean
    public LocalInterfaceFileLoader localInterfaceFileLoader() {
        return new LocalInterfaceFileLoader("D:\\tmp");
    }

    @Bean
    public ApplicationRunner initCache(ApplicationContext context) {
        return args -> {
            Map<String, ICacheService> caches = context.getBeansOfType(ICacheService.class);
            caches.values().stream().forEach(cache -> cache.reload());
        };
    }

    @Primary
    @Bean
    public BatchApplicationListener batchApplicationListener() {
        return new BatchApplicationListener() {
            @Override
            public void onApplicationEvent(ContextRefreshedEvent event) {
            }

            @Override
            public void stop() {
            }

            @Override
            public void handle(ServerEvent event) {
            }
        };
    }

    @Primary
    @Bean(initMethod = "stop")
    public JoyinBatchMainPolling joyinBatchMainPolling() {
        return new JoyinBatchMainPolling();
    }

    @Primary
    @Bean(initMethod = "stop")
    public JoyinBatchQuartzPolling joyinBatchQuartzPolling() {
        return new JoyinBatchQuartzPolling();
    }

}
