package com.xubo.druid.controller;

import com.alibaba.fastjson.JSONObject;
import com.xubo.druid.service.TestjsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author xubo
 * @Date 2022/1/10 15:44
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestjsonService testjsonService;

    @PostMapping("/testRocketMQ")
    public JSONObject testRocketMQ() {
        return testjsonService.testRocketMQ();
    }


}
