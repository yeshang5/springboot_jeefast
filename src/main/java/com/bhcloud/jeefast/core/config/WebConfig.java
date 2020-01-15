package com.bhcloud.jeefast.core.config;

import com.bhcloud.jeefast.core.interceptor.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 相当于spring-mvc.xml
 * @author: BaiHao
 * @date: 2020-01-15 上午 11:36
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/admin/**");   //日志拦截器
    }
}
