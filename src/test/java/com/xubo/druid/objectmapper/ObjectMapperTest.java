package com.xubo.druid.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @Author xubo
 * @Date 2022/1/13 11:13
 * ObjectMapper  实体类 String 互转  JacksonTypeHandler 类里面有使用到
 * https://www.cnblogs.com/xuwenjin/p/8976696.html
 *
 * FastJsonTypeHandler  JacksonTypeHandler 的 区别
 * https://blog.csdn.net/Dream_Weave/article/details/109782535
 *
 * ObjectMapper 简介
 * https://blog.csdn.net/qq_41834086/article/details/111152470
 *
 * Mybatis&Mybatisplus 优雅存取JSON
 * https://blog.csdn.net/qq271859852/article/details/103330914
 */
public class ObjectMapperTest {

    public static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Test
    public void testObjMapper() {
        UserInfo userInfo = UserInfo.builder().id(1)
                .name("Druid")
                .address("深圳")
                .tels(Arrays.asList("156", "186"))
                .build();
        try {
            mapper.writeValue(new File("E:\\xubo\\druidprojectprint.txt"), userInfo);

            String jsonStr = mapper.writeValueAsString(userInfo);
            System.out.println("str : " + jsonStr);

            byte[] bytes = mapper.writeValueAsBytes(userInfo);
            System.out.println("bytes : " + bytes);

            UserInfo info = mapper.readValue(jsonStr, UserInfo.class);
            System.out.println("info" + info);

            UserInfo info1 = mapper.readValue(bytes, UserInfo.class);
            System.out.println("info1" + info1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testList() {
        UserInfo userInfo = UserInfo.builder().id(1)
                .name("Druid")
                .address("深圳")
                .tels(Arrays.asList("156", "186"))
                .build();
        List<UserInfo> dateList = Arrays.asList(UserInfo.builder().id(1).name("Druid").address("深圳").tels(Arrays.asList("156", "186")).build(),
                UserInfo.builder().id(2).name("druid").address("广州").tels(Arrays.asList("183", "188")).build(),
                UserInfo.builder().id(3).name("snails").address("武汉").tels(Arrays.asList("158", "196")).build(),
                UserInfo.builder().id(4).name("Rocket").address("孝感").tels(Arrays.asList("187", "143")).build());
        try {
            String jsonStr = mapper.writeValueAsString(dateList);
            System.out.println(jsonStr);

            List<UserInfo> infoList = mapper.readValue(jsonStr, List.class);
            System.out.println(infoList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMap() {
        Map<String,Object> map = new HashMap<>();
        map.put("name", "Druid");
        map.put("age", 26);
        map.put("date", LocalDateTime.now());
        map.put("userInfo", UserInfo.builder().id(1).name("xubo").address("武汉").tels(Arrays.asList("156", "186")).build());

        try {
            String josnStr = mapper.writeValueAsString(map);
            System.out.println(josnStr);

            Map formatMap = mapper.readValue(josnStr, Map.class);
            System.out.println(formatMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOther() {
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        UserInfo userInfo = UserInfo.builder().id(1).name("druid").address("深圳").tels(Arrays.asList("156","186")).time(new Date()).build();

        try {
            String jsonStr = mapper.writeValueAsString(userInfo);
            System.out.println(jsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

}
