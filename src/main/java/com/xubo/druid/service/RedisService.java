package com.xubo.druid.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author xubo
 * @Date 2022/1/26 9:04
 */
public interface RedisService {

    JSONObject useRedisLock();

    JSONObject useRedissonLock();

    JSONObject useRedisLockToUpdateDB();

    JSONObject executeByOrder();

    JSONObject getLock();
}
