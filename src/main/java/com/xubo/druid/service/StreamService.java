package com.xubo.druid.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author xubo
 * @Date 2022/3/21 10:56
 */
public interface StreamService {

    public JSONObject reduce(JSONObject jsonObject);

    public JSONObject peek(JSONObject jsonObject);

}
