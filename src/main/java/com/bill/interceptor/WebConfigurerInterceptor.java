package com.bill.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 注册拦截器
 * @author ctfeng
 * @date 2018-04-23 14:30
 */
@Configuration
public class WebConfigurerInterceptor extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        registry.addInterceptor(new SSOInterceptor()).addPathPatterns("/api/**");
        super.addInterceptors(registry);
    }

}
