package com.xubo.druid.utils;

import com.alibaba.fastjson.JSONObject;
import com.xubo.druid.entity.domain.Student;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xubo
 * @Date 2022/3/7 9:26
 */
public class UseBeanUtils {

    /**
     * 写了一个工具类 实现List 的复制
     */
    @Test
    public void testBeanUtils() {
        List<Student> stringList = Arrays.asList(Student.builder().sname("Druid").ssex("nan").build(),
                Student.builder().sname("Druid1").ssex("nan1").build(),
                Student.builder().sname("Druid2").ssex("nan2").build(),
                Student.builder().sname("Druid3").ssex("nan4").build(),
                Student.builder().sname("Druid4").ssex("nan5").build());
        List<StudentVo> targetList = new ArrayList<>();
        ListUtils<StudentVo> listUtils = BeanUtils.instantiateClass(ListUtils.class);
        listUtils.copyList(stringList, targetList, StudentVo.class);
        System.out.println(targetList);
    }

    /**
     * 不可以复制 集合
     */
    @Test
    public void useBeanUtils() {
        List<Student> stringList = Arrays.asList(Student.builder().sname("Druid").ssex("nan").build(),
                Student.builder().sname("Druid1").ssex("nan1").build(),
                Student.builder().sname("Druid2").ssex("nan2").build(),
                Student.builder().sname("Druid3").ssex("nan4").build(),
                Student.builder().sname("Druid4").ssex("nan5").build());
        List<StudentVo> targetList = new ArrayList<>();
        BeanUtils.copyProperties(stringList, targetList);
        System.out.println(targetList);
    }

}
