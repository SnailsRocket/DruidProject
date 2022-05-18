package com.xubo.druid.handler;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author xubo
 * @Date 2022/5/18 11:16
 * 自定义过滤器
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 可以添加多个过滤器(一个权限，一个日志)，在拦截器中对请求做了预处理(或者)
        registry.addInterceptor(new LoginInterceper())
                // 增加url的拦截路径，/** 是对所有请求做拦截
                .addPathPatterns("/**")
                // 排除url的拦截路径
                .excludePathPatterns("/login","/registry");
    }
}
