package com.xubo.druid.Env;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author xubo
 * @Date 2022/3/29 13:24
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestEnv {

    @Autowired
    Environment environment;

    @Test
    public void testEnv() {
        String[] activeProfiles = environment.getActiveProfiles();
        System.out.println(activeProfiles[0]);
    }


}
