package com.xubo.druid.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author xubo
 * @Date 2022/3/3 10:46
 */
public interface RabbitMQService {

    public JSONObject sendMessage(JSONObject jsonObject);

    public JSONObject sendDelayMsg(JSONObject jsonObject);
}
