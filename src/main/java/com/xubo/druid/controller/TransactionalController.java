package com.xubo.druid.controller;

import com.alibaba.fastjson.JSONObject;
import com.xubo.druid.service.TransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author xubo
 * @Date 2022/4/1 13:44
 */
@RestController
@RequestMapping("/transactional")
public class TransactionalController {

    @Autowired
    TransactionalService transactionalService;

    @PostMapping("/public")
    public JSONObject publicInvalid() {
        transactionalService.publicInvalid();
        return new JSONObject();
    }

    @PostMapping("/transfer")
    public JSONObject transferMethod() {
        transactionalService.transferMethod();
        return new JSONObject();
    }

    /**
     * 多线程修改数据库，如果线程中执行出现异常 那么@Transaction 注解失效
     * @return
     */
    @PostMapping("/mulError")
    public ResponseEntity mulUpdateDB() {
        transactionalService.mulUpdateError();
        return new ResponseEntity(HttpStatus.OK);
    }

}
