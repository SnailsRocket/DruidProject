package com.xubo.druid;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author xubo
 * @Date 2021/12/27 14:48
 * git详解   https://blog.csdn.net/qq_34609889/article/details/88733153  fetch + merger = pull 不建议使用pull(容易出问题)
 * 增量部署  远程调试
 * 详解Executors.newFixedThreadPool()   https://blog.csdn.net/qq_35029061/article/details/86716334
 * reastTemplate事故  https://blog.csdn.net/weixin_45431247/article/details/103627301     https://www.cnblogs.com/jimw/p/9037542.html  https://www.jianshu.com/p/2d05397688dd
 * 投票系统设计
 * test master
 * 集合 多线程
 * Redis MQ ES  Cloud
 *
 */
@SpringBootApplication
@MapperScan(basePackages = "com.xubo.druid.mapper")
@EnableFeignClients("com.xubo.druid.client")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
