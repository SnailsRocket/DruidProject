package com.xubo.druid.controller;

import com.alibaba.fastjson.JSONObject;
import com.xubo.druid.client.VoteSystem2Clients;
import com.xubo.druid.client.VoteSystemClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author xubo
 * @Date 2022/5/10 13:21
 */
@RestController
@RequestMapping("/feignClient")
public class FeignClientController {

    private static Logger logger = LoggerFactory.getLogger(FeignClientController.class);

    @Autowired
    VoteSystemClient voteSystemClient;

    @Autowired
    VoteSystem2Clients voteSystem2Clients;

    @GetMapping("/testClient")
    public ResponseEntity testClient() {
        JSONObject user = voteSystemClient.getUser();
        logger.info(user.toString());
        JSONObject user1 = voteSystem2Clients.getUser();
        logger.info(user1.toString());
        return ResponseEntity.ok(new JSONObject().fluentPut("u1", user).fluentPut("u2", user1));
    }

}
