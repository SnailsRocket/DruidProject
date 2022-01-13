package com.xubo.druid.handler;

import cn.hutool.core.lang.Assert;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
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
 *
 * Mybatis-plus 插入 查询的坑  null
 * https://www.jianshu.com/p/1fbaff7fb187
 * https://www.jianshu.com/p/7f8a5016c785
 *
 */
@MappedTypes({Object.class})
@MappedJdbcTypes({JdbcType.VARCHAR})
public class RemarkTypeHandler extends AbstractObjectTypeHandler<Object> {

    private static final Logger log = LoggerFactory.getLogger(RemarkTypeHandler.class);
    private static ObjectMapper mapper = new ObjectMapper();
    private Class<Object> type;

    public RemarkTypeHandler(Class<Object> type) {
        if(log.isTraceEnabled()) {
            log.trace("RemarkTypeHandler(" + type + ")");
        }
        Assert.notNull(type, "Type argument cannot be null", new Object[0]);
        this.type = type;
    }

    protected Object parse(String json) {
        try {
            return mapper.readValue(json, this.type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("failed");
        return null;
    }

    public static void setObjectMapper(ObjectMapper objectMapper) {
        Assert.notNull(objectMapper, "ObjectMapper should not be null", new Object[0]);
        RemarkTypeHandler.mapper = objectMapper;
    }

}
