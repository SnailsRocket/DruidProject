package com.xubo.druid.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xubo.druid.entity.domain.FooBar;
import com.xubo.druid.mapper.FooBarMapper;
import com.xubo.druid.service.TransactionalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @Author xubo
 * @Date 2022/4/1 13:45
 */
@Service
@Slf4j
public class TransactionalServiceImpl implements TransactionalService {

    @Autowired
    FooBarMapper fooBarMapper;

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

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
     * update1 回滚成功
     * update2 没有回滚
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer mulUpdateError() {
        FooBar fooBar = FooBar.builder().name("mul1-1").age(13).build();
        int update1 = fooBarMapper.update(fooBar, new UpdateWrapper<FooBar>().eq("id", 50));

        // update1 回滚成功 update2 没有回滚
        /*executorService.submit(() -> {
            FooBar fooBar1 = FooBar.builder().name("mul2").age(11).build();
            int update2 = fooBarMapper.update(fooBar1, new UpdateWrapper<FooBar>().eq("id", 51));
            int b = 10 / 0;
        });
        try {
            executorService.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        // 异常在线程里面  udpate1 update2 都没有回滚 事务失效
        List<CompletableFuture<Integer>> completableFutureList = Arrays.asList(FooBar.builder().name("mul2-1").age(14).build())
                .stream().map(e -> CompletableFuture.supplyAsync(() -> {
                    int update2 = fooBarMapper.update(e, new UpdateWrapper<FooBar>().eq("id", 51));
                    // int x = 10 / 0;
                    log.info("代码执行异常");
                    return update2;
                }, executorService)).collect(Collectors.toList());

        // 异常信息在外面 update1 回滚成 update2 回滚失败
        // int y = 10 / 0;
        return null;
    }

    @Override
    public Integer mulUpdateErrorPro() {
        FooBar fooBar = FooBar.builder().name("mul1-1").age(13).build();
        int update1 = fooBarMapper.update(fooBar, new UpdateWrapper<FooBar>().eq("id", 50));

        // update1 回滚成功 update2 没有回滚
        /*executorService.submit(() -> {
            FooBar fooBar1 = FooBar.builder().name("mul2").age(11).build();
            int update2 = fooBarMapper.update(fooBar1, new UpdateWrapper<FooBar>().eq("id", 51));
            int b = 10 / 0;
        });
        try {
            executorService.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        // 异常在线程里面  udpate1 update2 都没有回滚 事务失效
        List<CompletableFuture<Integer>> completableFutureList = Arrays.asList(FooBar.builder().name("mul2-1").age(14).build())
                .stream().map(e -> CompletableFuture.supplyAsync(() -> {
                    try {
                        int update2 = fooBarMapper.update(e, new UpdateWrapper<FooBar>().eq("id", 51));
                        // int x = 10 / 0;
                        log.info("代码执行异常");
                        return update2;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        return 1;
                    }
                }, executorService)).collect(Collectors.toList());
        return null;
    }

    /**
     * A 没有使用事务注解 B 使用事务注解
     * A方法异常 B也不会回滚事务
     * 所以会往DB里面插入一条数据
     *
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
     *
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
