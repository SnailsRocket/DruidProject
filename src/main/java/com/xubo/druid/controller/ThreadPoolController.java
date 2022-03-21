package com.xubo.druid.controller;

import com.alibaba.fastjson.JSONObject;
import com.xubo.druid.service.ThreadPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


/**
 * @Author xubo
 * @Date 2022/3/8 9:19
 */
@RestController
@RequestMapping("/thread")
public class ThreadPoolController {

    @Autowired
    ThreadPoolService threadPoolService;

    @RequestMapping("/execute")
    public JSONObject execute(@RequestBody JSONObject jsonObject) {
        return threadPoolService.execute(jsonObject);
    }

    @PostMapping("/completableFeture")
    public JSONObject completableFeture(@RequestBody JSONObject jsonObject) {
        return threadPoolService.executeByCompletableFeture(jsonObject);
    }

    @PostMapping("/supplyAsync")
    public JSONObject supplyAsync(@RequestBody JSONObject jsonObject) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        Integer a = Integer.valueOf(10);
        String b = "";

        list.forEach(e -> e.equals(b));
        return threadPoolService.executeByCompletableFeture1(jsonObject);
    }

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        Integer a = Integer.valueOf(10);
        String b = "";

        list.forEach(e -> e.equals(b));

    }
}
