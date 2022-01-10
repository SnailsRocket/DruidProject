package com.xubo.druid.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xubo.druid.entity.domain.Testjson;
import com.xubo.druid.mapper.TestjsonMapper;
import com.xubo.druid.service.TestjsonService;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class TestjsonServiceImpl extends ServiceImpl<TestjsonMapper, Testjson>
    implements TestjsonService {

    @Override
    public JSONObject testRocketMQ() {

        return null;
    }
}




