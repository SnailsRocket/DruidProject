package com.xubo.druid.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xubo.druid.entity.domain.Testjson;

/**
 *
 */
public interface TestjsonService extends IService<Testjson> {
    JSONObject testRocketMQ();
}
