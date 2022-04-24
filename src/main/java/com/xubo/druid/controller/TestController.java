package com.xubo.druid.controller;

import com.alibaba.fastjson.JSONObject;
import com.xubo.druid.entity.domain.Bar;
import com.xubo.druid.entity.domain.FooBar;
import com.xubo.druid.service.FooBarService;
import com.xubo.druid.service.TestjsonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api("test")
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
    @ApiOperation("/查询 foo_bar 表")
    public JSONObject testHandler() {
        return new JSONObject().fluentPut("date", fooBarService.list());
    }

    @PutMapping("/insertHandler")
    @ApiOperation("新增数据")
    public JSONObject insertHandler() {
        List<FooBar> fooBars = fillParams();
        return new JSONObject().fluentPut("result", fooBarService.saveBatch(fooBars));
    }

    @PostMapping("/updateHandler")
    @ApiOperation("修改数据")
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
        for (int i = 0; i < 5; i++) {
            FooBar fooBar = FooBar.builder().description("sanguan" + i)
                    .name("ye" + i)
                    .age(i + 20)
                    .bar(Bar.builder().id(i).tel("156").address("武汉").build())
                    .remark(new HashMap<String, Object>(){{
                        put("remark", "备注");
                        put("number", 10);
                        put("updateTime", LocalDateTime.now());
                    }})
                    .createTime(LocalDateTime.now())
                    .build();
            paramsList.add(fooBar);
        }

        return paramsList;
    }

    public static void main(String[] args) {
        Integer in = Integer.valueOf(0);
        Integer ou = Integer.valueOf(0);
        System.out.println(in.compareTo(ou));

    }

}
