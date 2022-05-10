package com.xubo.druid.config;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Author xubo
 * @Date 2022/2/22 19:53
 */
@Configuration
@ConditionalOnClass(RedisTemplate.class)
public class RedisAutoConfiguration {

    @Value("${spring.redis.host}")
    private String redisAddress;

    @Value("${spring.redis.port}")
    private String redisPort;

    @Bean
    public Redisson redisson() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + redisAddress + StringPool.COLON + redisPort)
                //.setPassword()
                .setDatabase(0);
        return (Redisson) Redisson.create(config);
    }

    public static void main(String[] args) {
        Integer inte = new Integer(10);
        Integer integer = new Integer(9);
        Integer integer1 = new Integer(10);
        Integer integer2 = new Integer(12);
        System.out.println("inte.compareTo(integer) = " + inte.compareTo(integer));
        System.out.println("inte.compareTo(integer1) = " + inte.compareTo(integer1));
        System.out.println("inte.compareTo(integer2) = " + inte.compareTo(integer2));


    }

}
