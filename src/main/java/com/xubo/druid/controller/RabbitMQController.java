package com.xubo.druid.controller;

import com.alibaba.fastjson.JSONObject;
import com.xubo.druid.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author xubo
 * @Date 2022/3/3 10:43
 * RabbitMQ 延时队列 实现
 */
@RestController
@RequestMapping("/mq")
public class RabbitMQController {

    @Autowired
    RabbitMQService rabbitMQService;

    /**
     * 发送消息
     * @param jsonObject
     * @return
     */
    @PostMapping("/sendMsg")
    public JSONObject sendMsg(@RequestBody JSONObject jsonObject) {
        return rabbitMQService.sendMessage(jsonObject);
    }

    /**
     * 延时队列实现
     * @param jsonObject
     * @return
     */
    @PostMapping("sendDelayMsg")
    public JSONObject sendDelayMsg(@RequestBody JSONObject jsonObject) {
        return rabbitMQService.sendDelayMsg(jsonObject);
    }

}
