package com.xubo.druid.client;

import com.alibaba.fastjson.JSONObject;
import com.xubo.druid.config.FeignConfig1;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author xubo
 * @Date 2022/5/10 13:19
 */
//@FeignClient(value = "voteSystem1", url = "192.168.2.96:8081", configuration = FeignConfig1.class)
@FeignClient(value = "cmb", url = "192.168.2.96:8081", configuration = FeignConfig1.class)
public interface VoteSystem2Clients {

    @GetMapping("/test/getAllUser")
    public JSONObject getUser();

}
