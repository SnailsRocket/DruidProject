package com.xubo.druid;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xubo.druid.entity.domain.Testjson;
import com.xubo.druid.service.TestjsonService;
import com.xubo.druid.util.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Author xubo
 * @Date 2021/12/27 15:14
 * git详解   https://blog.csdn.net/qq_34609889/article/details/88733153  fetch + merger = pull 不建议使用pull(容易出问题)
 * 增量部署  远程调试
 * 详解Executors.newFixedThreadPool()   https://blog.csdn.net/qq_35029061/article/details/86716334  测试test命令
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    TestjsonService testjsonService;

    @Autowired
    RedisUtils redisUtils;

    @Test
    public void updateRemark() {
        new RestTemplate();
        Testjson testjson = new Testjson();
        testjson.setRemark(new HashMap<String,Integer>(){{ put("is_user",1); }});
        boolean update = testjsonService.update(testjson,new LambdaUpdateWrapper<Testjson>()
                /*.set(Testjson::getName, "xubo")
                .set(Testjson::getRemark, new HashMap<String, Integer>() {{
                    put("is_success", 1);
                }})*/
                .eq(Testjson::getId, 1));
        System.out.println(update);

    }


    @Test
    public void updateRemark1() {
        new RestTemplate();
        Testjson testjson = new Testjson();
        testjson.setName("fsfds");
        testjson.setTestnull("null");
        boolean save = testjsonService.save(testjson);
        System.out.println(save);

    }


    @Test
    public void setParams() {
        Testjson testjson = new Testjson();
        testjson.setName(null);
        testjson.setAge(12);
        System.out.println(testjson);
    }


    /**
     * 设置过期时间为3分钟，每次刷新就更新过期时间
     */
    @Test
    public void testRedis() {
        long time = System.currentTimeMillis();
        redisUtils.set("18627837596", time, 60);
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = redisUtils.get("18627837596");
        System.out.println(result);
        try {
            Thread.sleep(400000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result1 = redisUtils.get("18627837596");
        System.out.println(result1);
    }


}
