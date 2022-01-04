package com.xubo.druid;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author xubo
 * @Date 2021/12/27 14:48
 * git详解   https://blog.csdn.net/qq_34609889/article/details/88733153  fetch + merger = pull 不建议使用pull(容易出问题)
 * 增量部署  远程调试
 */
@SpringBootApplication
@MapperScan(basePackages = "com.xubo.druid.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
