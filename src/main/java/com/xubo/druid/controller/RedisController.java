package com.xubo.druid.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xubo.druid.entity.domain.Student;
import com.xubo.druid.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author xubo
 * @Date 2022/1/26 9:04
 */
@RestController
@RequestMapping("/redis")
@Api(value = "Redis接口")
public class RedisController {

    @Autowired
    RedisService redisService;

    public static void main(String[] args) {
        List<Integer> arrsList = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> first = arrsList.stream().filter(e -> e > 10).findFirst();
        System.out.println(first.isPresent());

        String str = "ec27fe50bb864c96a465fb1d44795530";
        System.out.println(str.length());
        String strs = null;
        JSONObject jsonObject = JSON.parseObject(strs, JSONObject.class);
        System.out.println(jsonObject);
        System.out.println("-------------------------------");

        Student build = Student.builder().sid("1").ssex("男").sbirth("2022-02-10").build();
        Class<? extends Student> buildClass = build.getClass();
        Map<String, Object> paramMap = new HashMap<>();
        for (Field declaredField : buildClass.getDeclaredFields()) {
            String[] split = declaredField.toString().split("\\.");
            System.out.println(split[split.length-1]);
            try {
                paramMap.put(split[split.length-1], declaredField.get(build));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        Set<String> keySet = paramMap.keySet();
    }

    @ApiOperation(value = "redisson 操作锁")
    @GetMapping("/lock")
    public JSONObject useRedisLock() {
        return redisService.useRedissonLock();
    }

    @GetMapping("/order")
    public JSONObject executeByOrder() {
        return redisService.executeByOrder();
    }


}
