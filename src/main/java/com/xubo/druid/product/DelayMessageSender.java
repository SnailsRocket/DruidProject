package com.xubo.druid.product;

import com.xubo.druid.config.RabbitMQBindConfig;
import com.xubo.druid.enums.DelayTypeEnum;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author xubo
 * @Date 2022/4/2 14:28
 */
@Component
public class DelayMessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String msg, DelayTypeEnum type) {
        switch (type) {
            case DELAY_10s:
                rabbitTemplate.convertAndSend(RabbitMQBindConfig.DELAY_EXCHANGE_NAME, RabbitMQBindConfig.DELAY_QUEUEA_ROUTING_KEY, msg);
                break;
            case DELAY_60s:
                rabbitTemplate.convertAndSend(RabbitMQBindConfig.DELAY_EXCHANGE_NAME, RabbitMQBindConfig.DELAY_QUEUEB_ROUTING_KEY, msg);
                break;
        }
    }

}
