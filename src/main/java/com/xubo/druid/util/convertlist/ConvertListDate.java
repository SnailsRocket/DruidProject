package com.xubo.druid.util.convertlist;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author xubo
 * @Date 2022/1/25 11:15
 * 直接copy 整个List数据 到另一个List数据 有一个条件，就是两个对象的属性名必须一直才可以，如果不一致就为null
 * 使用ObjectMapper 进行操作的
 * JacksonTypeHandler 这个里面也是使用的ObjectMapper
 */
public class ConvertListDate {

    public static void main(String[] args) {
        List<DateConvertSource> dateConvertSourceList = new ArrayList<>();
        fillDate(dateConvertSourceList);
        List<DateConvertTarget> dateConvertTargetList = copyListDate(dateConvertSourceList, new TypeReference<List<DateConvertTarget>>(){});
        System.out.println(dateConvertTargetList);
        System.out.println(dateConvertSourceList.size());

    }

    public static <T> List<T> copyListDate(List dateConvertSourceList, TypeReference typeReference) {
        ObjectMapper mapper = new ObjectMapper();
        List<T> list = null;
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
         try {
             list = (List<T>)mapper.readValue(mapper.writeValueAsString(dateConvertSourceList), typeReference);
         } catch (IOException e) {
             e.printStackTrace();
         }
         return list;
    }

    public static void fillDate(List<DateConvertSource> dateConvertSourceList) {
        for (int i = 0; i < 10; i++) {
            DateConvertSource source = DateConvertSource.builder()
                    .name("druid " + i)
                    .age(i)
                    .address("深圳 " + i)
                    .tel("1234" + i)
                    .description("test")
                    .build();
            dateConvertSourceList.add(source);
        }
    }
}
