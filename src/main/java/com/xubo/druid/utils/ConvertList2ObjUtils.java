package com.xubo.druid.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * @Author xubo
 * @Date 2022/5/20 15:34
 */
public class ConvertList2ObjUtils {

    private static Logger logger = LoggerFactory.getLogger(ConvertList2ObjUtils.class);

    public static <T> List<T> convertList2Obj(List dateConvertSourceList, TypeReference typeReference) {
        ObjectMapper mapper = new ObjectMapper();
        List<T> list = null;
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            list = (List<T>) mapper.readValue(mapper.writeValueAsString(dateConvertSourceList), typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
