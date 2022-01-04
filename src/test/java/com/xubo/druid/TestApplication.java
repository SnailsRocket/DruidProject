package com.xubo.druid;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xubo.druid.entity.domain.Testjson;
import com.xubo.druid.service.TestjsonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

/**
 * @Author xubo
 * @Date 2021/12/27 15:14
 * git详解   https://blog.csdn.net/qq_34609889/article/details/88733153  fetch + merger = pull 不建议使用pull(容易出问题)
 * 增量部署  远程调试
 * 详解Executors.newFixedThreadPool()   https://blog.csdn.net/qq_35029061/article/details/86716334
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplication {

    @Autowired
    TestjsonService testjsonService;

    @Test
    public void updateRemark() {
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


}
