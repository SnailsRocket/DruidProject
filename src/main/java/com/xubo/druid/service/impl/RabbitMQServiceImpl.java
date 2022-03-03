package com.xubo.druid.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xubo.druid.service.RabbitMQService;
import org.springframework.stereotype.Service;

/**
 * @Author xubo
 * @Date 2022/3/3 10:46
 */
@Service
public class RabbitMQServiceImpl implements RabbitMQService {


    @Override
    public JSONObject sendMessage(JSONObject jsonObject) {

        return null;
    }

    @Override
    public JSONObject sendDelayMsg(JSONObject jsonObject) {

        return null;
    }
}
