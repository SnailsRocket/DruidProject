package com.xubo.druid.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author xubo
 * @Date 2022/1/26 9:04
 */
public interface RedisService {

    JSONObject useRedisLock();

    JSONObject useRedisLockToUpdateDB();

    JSONObject executeByOrder();
}
