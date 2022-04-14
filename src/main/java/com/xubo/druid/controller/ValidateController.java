package com.xubo.druid.controller;

import com.alibaba.fastjson.JSONObject;
import com.xubo.druid.entity.ValidateEntity;
import com.xubo.druid.entity.domain.Bar;
import com.xubo.druid.entity.domain.FooBar;
import com.xubo.druid.entity.domain.Student;
import com.xubo.druid.service.BuilderService;
import com.xubo.druid.service.impl.BuilderServiceImpl;
import com.xubo.druid.util.DateUtils;
import com.xubo.druid.validate.AddGroup;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author xubo
 * @Date 2022/4/7 17:13
 * Hibernater 参数校验
 * 关于 Hibernate-validation 的用法
 * 参考 ：http://www.javashuo.com/article/p-uavnoqla-kz.html
 * 校验字符串的长度使用 @Size  校验数字的大小使用 @Max @Min
 * 常见的group 有AddGroup UpdateGroup 也可自定义 group
 * 控制器入参需要使用 @Validated 或者 @Valid 修饰
 * Valid 与 Validate 的区别
 * Valid 没有分组的功能
 * Validate(group = {AddGroup.class})  只校验实体类中标记了 AddGroup.class 的属性 没标记就都要校验  Validate 是 Valid 的一种封装
 *
 * 自定义group
 *
 */
@RestController
@RequestMapping("/validate")
//@Validated
public class ValidateController {


    @PostMapping("/add")
    public JSONObject addValidate(@RequestBody @Validated ValidateEntity validateEntity) {
        System.out.println("validateEntity = " + validateEntity);
        return new JSONObject().fluentPut("validateEntity: ", validateEntity);
    }

    /**
     * 消耗积分，从时间最久的一批开始
     * @param args
     */
    public static void main(String[] args) {
        BuilderService builderService = new BuilderServiceImpl();

        Student build = builderService.processOne().processTwo().processThree().build();
        System.out.println("build = " + build);

        Integer i = Integer.valueOf(1);
        Integer j = Integer.valueOf(0);
        System.out.println("i.compareTo(j) = " + i.compareTo(j));


        ArrayList<FooBar> fooBars = fillList();

        Integer count = Integer.valueOf(150);
        Integer temp = Integer.valueOf(0);

        List<FooBar> sortedList = fooBars.stream().sorted(Comparator.comparing(FooBar::getCreateTime)).collect(Collectors.toList());
        System.out.println("sortedList = " + sortedList);
        ArrayList<FooBar> cosumerList = new ArrayList<>();
        ArrayList<FooBar> tempList = new ArrayList<>();
        ArrayList<FooBar> unCosumerList = new ArrayList<>();
        for (FooBar fooBar : sortedList) {
            count = count - fooBar.getAge();
            System.out.println("count = " + count);
            System.out.println("fooBar.getAge() = " + fooBar.getAge());
            if(count > 0) {
                cosumerList.add(fooBar);
            } else if(count == 0) {
                cosumerList.add(fooBar);
                return;
            } else if(count < 0) {
                tempList.add(fooBar);
                break;
            }
        }
        System.out.println("cosumerList = " + cosumerList);
        System.out.println("tempList = " + tempList);

    }

    public static ArrayList<FooBar> fillList() {
        ArrayList<FooBar> fooBarArrayList = new ArrayList<>();
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.add(Calendar.DAY_OF_MONTH, -2); // 12
        fooBarArrayList.add(FooBar.builder().age(30).createTime(DateUtils.dateToLocalDateTime(instance.getTime())).build());
        instance.add(Calendar.DAY_OF_MONTH, -1); // 11
        fooBarArrayList.add(FooBar.builder().age(40).createTime(DateUtils.dateToLocalDateTime(instance.getTime())).build());
        instance.add(Calendar.DAY_OF_MONTH,-1); // 10
        fooBarArrayList.add(FooBar.builder().age(30).createTime(DateUtils.dateToLocalDateTime(instance.getTime())).build());
        instance.add(Calendar.DAY_OF_MONTH, -1); // 9
        fooBarArrayList.add(FooBar.builder().age(20).createTime(DateUtils.dateToLocalDateTime(instance.getTime())).build());
        instance.add(Calendar.DAY_OF_MONTH, -1); // 8
        fooBarArrayList.add(FooBar.builder().age(90).createTime(DateUtils.dateToLocalDateTime(instance.getTime())).build());
        return fooBarArrayList;
    }


}
