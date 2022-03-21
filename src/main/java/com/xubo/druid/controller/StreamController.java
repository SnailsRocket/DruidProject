package com.xubo.druid.controller;

import com.alibaba.fastjson.JSONObject;
import com.xubo.druid.service.StreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author xubo
 * @Date 2022/3/21 10:54
 */
@RestController
@RequestMapping("/stream")
public class StreamController {

    @Autowired
    StreamService streamService;

    @PostMapping("/reduce")
    public JSONObject reduceFunction(@RequestBody JSONObject jsonObject) {
        return streamService.reduce(jsonObject);
    }

    @PostMapping("/peek")
    public JSONObject peekFunction(@RequestBody JSONObject jsonObject) {
        return streamService.peek(jsonObject);
    }

}
