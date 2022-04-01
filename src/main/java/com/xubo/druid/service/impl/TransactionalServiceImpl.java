package com.xubo.druid.service.impl;

import com.xubo.druid.entity.domain.FooBar;
import com.xubo.druid.mapper.FooBarMapper;
import com.xubo.druid.service.TransactionalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author xubo
 * @Date 2022/4/1 13:45
 */
@Service
@Slf4j
public class TransactionalServiceImpl implements TransactionalService {

    @Autowired
    FooBarMapper fooBarMapper;

    /**
     * 事务失效
     * 下面方法执行错误依旧往DB里面插入一条数据
     */
    @Override
    public void publicInvalid() {
        this.updateDB(Integer.valueOf(12));
        System.out.println(10 / 0);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transferMethod() {
        try {
            this.updateDBWithError(Integer.valueOf(16));
        } catch (Exception e) {
            log.info("error：" + e.toString());
        }
    }

    /**
     * A 没有使用事务注解 B 使用事务注解
     * A方法异常 B也不会回滚事务
     * 所以会往DB里面插入一条数据
     * @param age
     */
    @Transactional(rollbackFor = Exception.class)
    private void updateDB(Integer age) {
        log.info("事务开始执行！");
        fooBarMapper.insert(FooBar.builder().name("desc").age(age).build());
        log.info("事务执行结束！");
    }

    /**
     * A B 两个方法都用 Transactional 修饰 A调用 B  B 执行异常，但是事务没有回滚，所以会往DB里面插入一条数据
     * @param age
     */
    @Transactional(rollbackFor = Exception.class)
    private void updateDBWithError(Integer age) {
        log.info("事务开始执行！");
        fooBarMapper.insert(FooBar.builder().name("desc").age(age).build());
        log.info("事务执行结束！");
        System.out.println(10 / 0);
    }

}
