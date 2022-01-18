package com.xubo.druid.jdkfeature;

import org.junit.Test;

import java.util.Optional;

/**
 * @Author xubo
 * @Date 2022/1/17 15:18
 */
public class OptionTest {

    @Test
    public void testOption() {
        System.out.println(Optional.ofNullable(null).isPresent());
    }


    @Test
    public void testString() {
        String str = "上海";
        String res = str.replace("市", "");
        System.out.println(res);
    }


}
