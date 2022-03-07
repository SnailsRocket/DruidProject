package com.xubo.druid.utils;


import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;

/**
 * @Author xubo
 * @Date 2022/3/7 9:26
 * BeanUtils copy list
 */
public class ListUtils<T> {
    public void copyList(Object obj, List<T> targetList, Class<T> targetObjClass) {
        if((!Objects.isNull(obj)) && (!Objects.isNull(targetList))) {
            List sourceList = (List) obj;
            sourceList.stream().forEach(source -> {
                T target = null;
                try {
                    target = targetObjClass.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                BeanUtils.copyProperties(source, target);
                targetList.add(target);
            });
        }
    }
}
