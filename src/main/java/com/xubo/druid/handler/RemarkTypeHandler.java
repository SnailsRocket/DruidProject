package com.xubo.druid.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author xubo
 * @Date 2022/1/13 16:03
 * 手动实现一个TypeHandler
 * FastJsonTypeHandler 与 JacksonTypeHandler 的区别
 * JacksonTypeHandler 源码里面有 ObjectMapper 这个类的功能是 将实体类和 JSON互转 参考 ObjectMapperTest
 * https://blog.csdn.net/Dream_Weave/article/details/109782535
 * 一定要加上 @TableName(autoResultMap = true)
 * 使用 RemarkTypeHandler 或者 JacksonTypeHandler都可以
 */
public class RemarkTypeHandler extends AbstractObjectTypeHandler<HashMap<String,Object>> {

    private static final Logger log = LoggerFactory.getLogger(RemarkTypeHandler.class);
    private static ObjectMapper mapper = new ObjectMapper();
    private Class<Object> type;

    public HashMap<String, Object> parse(String json) {
        try {
            return (HashMap<String, Object>) mapper.readValue(json, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("failed");
        return null;
    }

    public String toJson(HashMap<String, Object> map) {
        try {
            return mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("failed");
        return null;
    }

}
