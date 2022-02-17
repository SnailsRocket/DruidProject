package com.xubo.druid.util;

import com.xubo.druid.entity.domain.Student;
import com.xubo.druid.entity.pay.LoginNotify;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author xubo
 * @Date 2022/2/17 13:56
 * 基于反射机制
 */
@Slf4j
public class ClassCopyUtils {

    /**
     * 笔记： 思路有问题，传过来字节码，然后通过newInstance() 方法创建对象
     * @param object 对象的字节码
     * @param <T>
     * @return
     */
    public static Map<String, String> getMapFromObjectByReflect(Object object)  {
        Map<String, String> paramMap = new HashMap<>();
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            String name = declaredField.getName();
            String simpleName = declaredField.getType().getSimpleName();
            System.out.println(name + " : " + simpleName);
            Object objValue = null;
            try {
                objValue = declaredField.get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            paramMap.put(name, String.valueOf(objValue));
        }
        return paramMap;
    }

    public static void main(String[] args) {
        LoginNotify build = LoginNotify.builder().userId("123456")
                .partner("1234567890")
                .mobile("15623267557")
                .data("2022.2.17")
                .createTime(new Date())
                .build();
        Map<String, String> mapFromObjectByReflect = getMapFromObjectByReflect(build);
        System.out.println(mapFromObjectByReflect);
    }

}
