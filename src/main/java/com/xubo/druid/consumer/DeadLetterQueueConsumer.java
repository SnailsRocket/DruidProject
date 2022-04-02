package com.xubo.druid.consumer;

import com.rabbitmq.client.Channel;
import com.xubo.druid.config.RabbitMQBindConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * @Author xubo
 * @Date 2022/4/2 14:25
 */
@Slf4j
@Component
public class DeadLetterQueueConsumer {

    /**
     * 消息在6秒后变成死信消息，然后被消费
     * @param message
     * @param channel
     * @throws IOException
     */
    @RabbitListener(queues = RabbitMQBindConfig.DEAD_LETTER_QUEUEA_NAME)
    public void receiveA(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("当前时间：{},死信队列A收到消息：{}", new Date().toString(), msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    /**
     * 消息在60s 后变成死信消息，然后被消费
     * @param message
     * @param channel
     * @throws IOException
     */
    @RabbitListener(queues = RabbitMQBindConfig.DEAD_LETTER_QUEUEB_NAME)
    public void receiveB(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("当前时间：{},死信队列B收到消息：{}", new Date().toString(), msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = RabbitMQBindConfig.DEAD_LETTER_QUEUEC_NAME)
    public void receiveC(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("当前时间：{},死信队列C收到消息：{}", new Date().toString(), msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

}
