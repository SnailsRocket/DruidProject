package com.xubo.druid.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author xubo
 * @Date 2022/4/15 15:55
 */
@RestController
@RequestMapping("/nacos")
public class TestNacosController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/port")
    public JSONObject test() {
        return new JSONObject().fluentPut("port", port);
    }

}
