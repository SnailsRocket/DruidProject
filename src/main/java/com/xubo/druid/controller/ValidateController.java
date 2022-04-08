package com.xubo.druid.controller;

import com.alibaba.fastjson.JSONObject;
import com.xubo.druid.entity.ValidateEntity;
import com.xubo.druid.validate.AddGroup;
import com.xubo.druid.validate.UpdateGroup;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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



}
