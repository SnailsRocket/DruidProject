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

/**
 * @Author xubo
 * @Date 2022/4/7 17:13
 * Hibernater 参数校验
 *
 */
@RestController
@RequestMapping("/validate")
public class ValidateController {


    @PostMapping("/add")
    public JSONObject addValidate(@RequestBody @Validated(value = {AddGroup.class}) ValidateEntity validateEntity) {
        System.out.println("validateEntity = " + validateEntity);
        return new JSONObject().fluentPut("validateEntity: ", validateEntity);
    }



}
