package com.xubo.druid.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.xubo.druid.config.ThreadPoolConfig;
import com.xubo.druid.service.ThreadPoolService;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @Author xubo
 * @Date 2022/3/8 9:22
 */
@SuppressWarnings("ALL")
@Service
public class ThreadPoolServiceImpl implements ThreadPoolService {

    @Resource(name = ThreadPoolConfig.MY_THREAD_POOL_TASK_EXECUTOR)
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;


    /**
     * 主线程 在子线程之前结束执行   submit 则是主线程等待子线程执行完，再处理后面的逻辑
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject execute(JSONObject jsonObject) {
        List<String> stringList = new ArrayList<>();
        List<String> strings = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15");
        SimpleDateFormat sdf = new SimpleDateFormat("ss SSS");
        long startTime = System.currentTimeMillis();
        threadPoolTaskExecutor.execute(() -> {
            strings.stream().forEach(e -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.out.println(e + " " + Thread.currentThread().getName() + ": " + Thread.currentThread().getId());
            });
        });
        long endTime = System.currentTimeMillis();
        System.out.println(sdf.format(endTime - startTime));
        return new JSONObject().fluentPut("cost time", sdf.format(endTime - startTime));
    }

    @SuppressWarnings("AlibabaThreadPoolCreation")
    @Override
    public JSONObject executeByCompletableFeture(JSONObject jsonObject) {
        List<String> strings = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17","18","19","20",
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17","18","19","20",
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        // 阿里巴巴Java 开发手册 中 强制 禁止 使用 Executors 创建线程 编程规范问题
        ExecutorService executorService = Executors.newFixedThreadPool(200);
        //noinspection AlibabaThreadPoolCreation
        Executors.newCachedThreadPool();
        List<List<String>> partitionList = Lists.partition(strings, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("ss SSS");
        long startTime = System.currentTimeMillis();
        List<CompletableFuture<List<String>>> collect = partitionList.stream().map(e -> CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println(e + ": " + Thread.currentThread().getName() + ": " + Thread.currentThread().getId());
            return e;
        }, executorService)).collect(Collectors.toList());
        long endTime = System.currentTimeMillis();
        System.out.println(sdf.format(endTime - startTime));
        return new JSONObject().fluentPut("cost time", sdf.format(endTime - startTime));
    }

    @SuppressWarnings("AlibabaThreadPoolCreation")
    @Override
    public JSONObject executeByCompletableFeture1(JSONObject jsonObject) {
        List<String> strings = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17","18","19","20","1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17","18","19","20","1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        // 不建议使用 Executors 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        Executors.newSingleThreadExecutor();
        List<List<String>> partitionList = Lists.partition(strings, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("ss SSS");
        long startTime = System.currentTimeMillis();
        List<CompletableFuture<List<String>>> collect = partitionList.stream().map(e -> CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println(e + ": " + Thread.currentThread().getName() + ": " + Thread.currentThread().getId());
            return e;
        }, threadPoolTaskExecutor)).collect(Collectors.toList());
        long endTime = System.currentTimeMillis();
        System.out.println(sdf.format(endTime - startTime));
        return new JSONObject().fluentPut("cost time", sdf.format(endTime - startTime));
    }

    public static void main(String[] args) {
        System.out.println(String.valueOf(new Date().getTime()));
        System.out.println(System.currentTimeMillis());

    }

}
