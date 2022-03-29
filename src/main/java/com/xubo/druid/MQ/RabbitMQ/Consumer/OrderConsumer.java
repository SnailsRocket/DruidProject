package com.xubo.druid.MQ.RabbitMQ.Consumer;

import com.xubo.druid.constant.MQConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Author xubo
 * @Date 2022/3/29 9:57
 */
@Component
@Slf4j
public class OrderConsumer {

    /*@RabbitListener(bindings = @QueueBinding(
            key = MQConstant.ORDER_EXCHANGE + "_QUEUE",
            value = @Queue(value = MQConstant.ORDER_EXCHANGE + "_QUEUE", durable = "true"),
            exchange = @Exchange(value = MQConstant.ORDER_EXCHANGE, durable = "true", type = ExchangeTypes.DIRECT)
    ))
    public void orderReceive(String receiveStr) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("接收到的参数: " + receiveStr);
    }*/


    @RabbitListener(bindings = @QueueBinding(
            key = MQConstant.ORDER_DELAY + "_QUEUE",
            value = @Queue(value = MQConstant.ORDER_DELAY + "_QUEUE", durable = "true"),
            exchange = @Exchange(value = MQConstant.ORDER_EXCHANGE, durable = "true", type = ExchangeTypes.DIRECT)
    ))
    public void delayOrderReceive(String receiveStr) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("延时接收：" + receiveStr);
    }

}
