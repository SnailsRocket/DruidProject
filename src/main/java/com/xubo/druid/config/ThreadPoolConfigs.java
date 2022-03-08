package com.xubo.druid.config;

import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author xubo
 * @Date 2022/3/8 13:38
 */
@Configuration
public class ThreadPoolConfigs {


    public static void main(String[] args) {
        Executors.newCachedThreadPool();

    }

}