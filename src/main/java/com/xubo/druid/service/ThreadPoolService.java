package com.xubo.druid.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author xubo
 * @Date 2022/3/8 9:22
 */
public interface ThreadPoolService {

    JSONObject execute(JSONObject jsonObject);

    JSONObject executeByCompletableFeture(JSONObject jsonObject);

    JSONObject executeByCompletableFeture1(JSONObject jsonObject);

}
