package com.xubo.druid.leetcode.juc.orderprint;

import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @Author xubo
 * @Date 2022/1/19 10:01
 * 多线程查询数据库并将多个线程查询出来的结果汇合到一个集合里面
 * 参考之前的博客， 回去找一下
 * CompletableFuture 特性  supplyAsync   join
 */
public class SupplyAsyncTest {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("ss SSS");

        // 多线程异步查询 执行时间是一次查询的时间
        MultiThread(sdf);

        //  同步顺序查询  执行时间 01  575  多次查询时间的总和
        // sequenceProcess(sdf);


    }


    /**
     * 多线程异步查询 执行时间是一次查询的时间
     * @param sdf
     */
    public static void MultiThread(SimpleDateFormat sdf) {
        List<JSONObject> resultList;
        ExecutorService pool = Executors.newFixedThreadPool(3);
        List<Integer> processList = Arrays.asList(1, 2, 3);
        long startTime = System.currentTimeMillis();
        List<CompletableFuture<List<JSONObject>>> completableFutureList = processList.stream().map(inputParam -> CompletableFuture.supplyAsync(
                () -> {
                    return processSelect(inputParam);
                }, pool
        )).collect(Collectors.toList());

        List<List<JSONObject>> joinList = completableFutureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
        resultList = joinList.stream().flatMap(inner -> inner.stream()).collect(Collectors.toList());
        long endTime = System.currentTimeMillis();
        System.out.println(sdf.format(endTime - startTime));
        pool.shutdown();
        System.out.println(pool.isShutdown());
        System.out.println(resultList.size());
    }

    /**
     * 同步顺序查询  执行时间 01  575  多次查询时间的总和
     * @param sdf
     */
    public static void sequenceProcess(SimpleDateFormat sdf) {
        long startTime = System.currentTimeMillis();
        List<JSONObject> selectOneList = selectOne();
        List<JSONObject> selectTwoList = selectTwo();
        List<JSONObject> selectThreeList = selectThree();
        long endTime = System.currentTimeMillis();
        System.out.println(sdf.format(endTime - startTime));
    }

    public static List<JSONObject> selectOne() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Arrays.asList(new JSONObject().fluentPut("name", "druid").fluentPut("age",12).fluentPut("address", "武汉"),
                new JSONObject().fluentPut("name", "xubo").fluentPut("age",16).fluentPut("address", "深圳"),
                new JSONObject().fluentPut("name", "snailsRocket").fluentPut("age",19).fluentPut("address", "苏州"),
                new JSONObject().fluentPut("name", "yang").fluentPut("age",22).fluentPut("address", "昆山"));
    }

    public static List<JSONObject> selectTwo() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Arrays.asList(new JSONObject().fluentPut("name", "liuziguan").fluentPut("age",29).fluentPut("address", "徐州"),
                new JSONObject().fluentPut("name", "zhuoli").fluentPut("age",29).fluentPut("address", "徐州"),
                new JSONObject().fluentPut("name", "beixiaoshui").fluentPut("age",24).fluentPut("address", "徐州"),
                new JSONObject().fluentPut("name", "chenzikun").fluentPut("age",90).fluentPut("address", "湖南"));
    }

    public static List<JSONObject> selectThree() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Arrays.asList(new JSONObject().fluentPut("name", "yedi").fluentPut("age",23).fluentPut("address", "武汉"),
                new JSONObject().fluentPut("name", "xb").fluentPut("age",26).fluentPut("address", "深圳"),
                new JSONObject().fluentPut("name", "houci").fluentPut("age",27).fluentPut("address", "苏州"),
                new JSONObject().fluentPut("name", "gaoqianjin").fluentPut("age",42).fluentPut("address", "昆山"));
    }

    public static List<JSONObject> processSelect(Integer processId) {
        return processId.equals(Integer.valueOf(1)) ? selectOne() : (processId.equals(Integer.valueOf(2)) ? selectTwo() : selectThree());
    }
}
