package com.xubo.druid.client;

import com.alibaba.fastjson.JSONObject;
import com.xubo.druid.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author xubo
 * @Date 2022/5/10 13:15
 */
//@FeignClient(value = "voteSystem", url = "192.168.2.96:8081", configuration = FeignConfig.class)
@FeignClient(value = "voteSystem", url = "192.168.2.96:8081", configuration = FeignConfig.class)
public interface VoteSystemClient {

    @GetMapping("/test/getAllUser")
    public JSONObject getUser();

}
