package com.xubo.druid;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author xubo
 * @Date 2021/12/27 14:48
 */
@SpringBootApplication
@MapperScan(basePackages = "com.xubo.druid.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
