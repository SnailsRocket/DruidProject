package com.xubo.druid.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.xubo.druid.entity.domain.Score;
import com.xubo.druid.entity.domain.Student;
import com.xubo.druid.service.RedisService;
import com.xubo.druid.service.ScoreService;
import com.xubo.druid.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RFuture;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

/**
 * @Author xubo
 * @Date 2022/1/26 9:04
 */
@Service
@Slf4j
public class RedisServiceImpl implements RedisService {

    @Autowired
    ScoreServiceImpl scoreService;

    @Autowired
    Redisson redisson;


    @Override
    public JSONObject useRedisLock() {
        // redisson.getLock();
        ReentrantLock reentrantLock = new ReentrantLock(true);
        List<Student> studentList = fillDate();
        List<List<Student>> partitionList = Lists.partition(studentList, 3);
        System.out.println(partitionList.size());
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        SimpleDateFormat sdf = new SimpleDateFormat("ss SS");
        long startTime = System.currentTimeMillis();

        if(reentrantLock.tryLock()) {
            try {
                List<CompletableFuture<List<Student>>> completableFutureList = partitionList.stream().map(e ->  CompletableFuture.supplyAsync(() -> {
                    e.stream().forEach(o -> {
                        o.setSsex("nv");
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    });
                    System.out.println(Thread.currentThread().getName());
                    return e;
                }, executorService)).collect(Collectors.toList());
                List<List<Student>> collectList = completableFutureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
                List<Student> students = collectList.stream().flatMap(inner -> inner.stream()).collect(Collectors.toList());
            } finally {
                log.info("释放锁！");
                reentrantLock.unlock();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println(sdf.format(endTime - startTime));

        return new JSONObject().fluentPut("useTime", sdf.format(endTime - startTime));
    }

    @Override
    public JSONObject useRedissonLock() {
        System.out.println(Thread.currentThread().getName());
        SimpleDateFormat sdf = new SimpleDateFormat("hh: MM: ss SSS");
        RLock lock = redisson.getLock("1");
        try {
            lock.lock();
            Thread.sleep(2000);
            System.out.println(System.currentTimeMillis());
            System.out.println(sdf.format(System.currentTimeMillis()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }

        return new JSONObject();
    }

    public List<Student> fillDate() {
        return Arrays.asList(Student.builder().ssex("nan").sname("xubo1").build(),
                Student.builder().ssex("nan").sname("xubo2").build(),
                Student.builder().ssex("nan").sname("xubo3").build(),
                Student.builder().ssex("nan").sname("xubo4").build(),
                Student.builder().ssex("nan").sname("xubo5").build(),
                Student.builder().ssex("nan").sname("xubo6").build(),
                Student.builder().ssex("nan").sname("xubo7").build(),
                Student.builder().ssex("nan").sname("xubo8").build(),
                Student.builder().ssex("nan").sname("xubo9").build(),
                Student.builder().ssex("nan").sname("xubo10").build(),
                Student.builder().ssex("nan").sname("xubo11").build(),
                Student.builder().ssex("nan").sname("xubo12").build());
    }

    @Override
    public JSONObject useRedisLockToUpdateDB() {
        // redisson.getLock();
        ReentrantLock reentrantLock = new ReentrantLock(true);
        List<Student> studentList = fillDate();
        //List<List<e>> partitionList = Lists.partition(studentList, 3);
        //System.out.println(partitionList.size());
        List<String> paramList = Arrays.asList("1", "2", "3");
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        SimpleDateFormat sdf = new SimpleDateFormat("ss SS");
        long startTime = System.currentTimeMillis();

                List<CompletableFuture<List<Score>>> completableFutureList = paramList.stream().map(e ->  CompletableFuture.supplyAsync(() -> {
                    List<Score> o = scoreService.getLists();
                    return o;
                }, executorService)).collect(Collectors.toList());
                List<List<Score>> collectList = completableFutureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
                List<Score> students = collectList.stream().flatMap(inner -> inner.stream()).collect(Collectors.toList());

        long endTime = System.currentTimeMillis();
        System.out.println(sdf.format(endTime - startTime));

        return new JSONObject().fluentPut("useTime", sdf.format(endTime - startTime));
    }

    @Override
    public JSONObject executeByOrder() {
        List<String> paramList = Arrays.asList("1", "2", "3");
        SimpleDateFormat sdf = new SimpleDateFormat("ss SS");
        long startTime = System.currentTimeMillis();
        paramList.stream().forEach(e -> {
            scoreService.getLists();
        });
        long endTime = System.currentTimeMillis();
        System.out.println(sdf.format(endTime - startTime));
        return new JSONObject().fluentPut("useTime", sdf.format(endTime - startTime));
    }

    @Override
    public JSONObject getLock() {
        //  lock 默认 公平锁
        RLock lock = redisson.getLock("anyLock");
        lock.lock();
        // 设置超时时间
        lock.lock(10, TimeUnit.SECONDS);
        // wait for 100 seconds and automatically unlock it after 10 seconds
        boolean res = false;
        try {
            res = lock.tryLock(100, 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (res) {
            try {
                System.out.println("tryLock");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        // 异步
        RLock asyncLock = redisson.getLock("asyncLock");
        asyncLock.lockAsync();
        asyncLock.lockAsync(10, TimeUnit.SECONDS);
        RFuture<Boolean> asyncRes = asyncLock.tryLockAsync(100, 10, TimeUnit.SECONDS);

        // fair lock
        RLock fairLock = redisson.getFairLock("fairLock");
        fairLock.lock();
        fairLock.lock(10, TimeUnit.SECONDS);
        try {
            boolean fairLockRes = fairLock.tryLock(100, 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        fairLock.unlock();

        // multiLock
        /*RLock lock1 = redisson1.getLock("mulLock1");
        RLock lock2 = redisson2.getLock("mulLock2");
        RLock lock3 = redisson3.getLock("mulLock3");
        RLock multiLock = redisson.getMultiLock(lock1, lock2, lock3);
        multiLock.lock();
        multiLock.unlock();*/




        return null;
    }

}
