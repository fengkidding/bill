package com.bill;

import com.bill.common.metric.TagTemplateContext;
import com.bill.config.FinalEnvConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

/**
 * 启动类
 *
 * @author f
 * @date 2019-03-10
 */
@SpringBootApplication
@EnableScheduling
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.bill.manager.client")
@EnableAsync
@EnableCaching(proxyTargetClass = true)
public class BillApplication {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${spring.profiles.active}")
    private String env;

    public static void main(String[] args) {
        SpringApplication.run(BillApplication.class, args);
    }

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        FinalEnvConfig.setAppName(appName);
        FinalEnvConfig.setEnv(env);
    }
}
