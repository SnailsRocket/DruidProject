package com.xubo.druid.config;

import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author xubo
 * @Date 2022/5/10 13:25
 */
@Configuration
public class FeignConfig1 implements RequestInterceptor {

    @Autowired
    RequestContextListener requestContextListener;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        requestTemplate.header(HttpHeaders.AUTHORIZATION,request.getHeader(HttpHeaders.AUTHORIZATION));
    }

    @Bean
    Logger.Level feignLoggerLevel2() {
        return Logger.Level.FULL;
    }

}
