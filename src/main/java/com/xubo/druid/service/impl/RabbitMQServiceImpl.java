package com.xubo.druid.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xubo.druid.config.RabbitMQConfig;
import com.xubo.druid.constant.MQConstant;
import com.xubo.druid.service.RabbitMQService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author xubo
 * @Date 2022/3/3 10:46
 */
@Service
@Slf4j
public class RabbitMQServiceImpl implements RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public JSONObject sendMessage(JSONObject jsonObject) {
        Map<String, String> paramMap = new HashMap(){{
            put("name", "xubo");
            put("age", "12");
            put("address", "深圳");}};
        log.info("发送消息到MQ: " + paramMap.toString());
        for (int i = 0; i < 500; i++) {
            rabbitTemplate.convertAndSend(MQConstant.ORDER_EXCHANGE, MQConstant.ORDER_EXCHANGE + "_QUEUE", paramMap.toString());

            rabbitTemplate.convertAndSend(MQConstant.ORDER_EXCHANGE, MQConstant.ORDER_DELAY + "_QUEUE", JSON.toJSONString(paramMap));
        }

        log.info("发送消息成功！" + JSON.toJSONString(paramMap));
        return new JSONObject();
    }

    @Override
    public JSONObject sendDelayMsg(JSONObject jsonObject) {
        Map<String, String> paramMap = new HashMap(){{
            put("name", "xubo");
            put("age", "12");
            put("address", "深圳");}};
        log.info(LocalDateTime.now().toString() + " - 发送消息到MQ: " + paramMap.toString());
        rabbitTemplate.convertAndSend(MQConstant.DELAY_QUEUE, MQConstant.DELAY_QUEUE + "_QUEUE", JSON.toJSONString(paramMap), a -> {
            a.getMessageProperties().setDelay(60000);
            return a;
        });
        return null;
    }
}
