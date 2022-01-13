package com.xubo.druid.controller;

import com.alibaba.fastjson.JSONObject;
import com.xubo.druid.entity.domain.Bar;
import com.xubo.druid.entity.domain.FooBar;
import com.xubo.druid.service.FooBarService;
import com.xubo.druid.service.TestjsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Author xubo
 * @Date 2022/1/10 15:44
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestjsonService testjsonService;

    @Autowired
    FooBarService fooBarService;

    @PostMapping("/testRocketMQ")
    public JSONObject testRocketMQ() {
        return testjsonService.testRocketMQ();
    }

    @PostMapping("/testHandler")
    public JSONObject testHandler() {
        return new JSONObject().fluentPut("date", fooBarService.list());
    }

    @PutMapping("/insertHandler")
    public JSONObject insertHandler() {
        List<FooBar> fooBars = fillParams();
        return new JSONObject().fluentPut("result", fooBarService.saveBatch(fooBars));
    }

    @PostMapping("/updateHandler")
    public JSONObject updateHandler() {
        List<FooBar> fooBarList = fooBarService.list();
        fooBarList.stream().forEach(e -> {
            e.setRemark(new HashMap<String, Object>(){{
                put("remark", "备注");
                put("number", 10);
                put("updateTime", LocalDateTime.now());
            }});
        });
        boolean b = fooBarService.updateBatchById(fooBarList);
        return new JSONObject().fluentPut("success", b);
    }

    public List<FooBar> fillParams() {
        List<FooBar> paramsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            FooBar fooBar = FooBar.builder().description("sanguan" + i)
                    .name("ye" + i)
                    .age(i + 20)
                    .bar(Bar.builder().id(i).tel("156").address("武汉").build())
                    .remark(new HashMap<String, Object>(){{
                        put("remark", "备注");
                        put("number", 10);
                        put("updateTime", LocalDateTime.now());
                    }})
                    .build();
            paramsList.add(fooBar);
        }

        return paramsList;
    }


}
