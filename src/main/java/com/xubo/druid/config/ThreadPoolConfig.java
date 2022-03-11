package com.xubo.druid.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author xubo
 * @Date 2022/3/8 9:18
 * 线程池
 */
@Configuration
public class ThreadPoolConfig {

    private static final String CUSTOM_THREAD = "CUSTOM THREAD";
    private static final String CUSTOM_THREAD_GROUP = "CUSTOM THREAD GROUP";
    private static final ThreadGroup systemThreadGroup = new ThreadGroup(ThreadPoolConfig.CUSTOM_THREAD_GROUP);
    public static final String MY_THREAD_POOL_TASK_EXECUTOR = "myThreadPoolTaskExecutor";
    private static final String STRING = "[#";
    private static final String STRING1 = "]";
    // 核心线程池大小
    private final int corePoolSize = 50;

    // 最大可创建的线程数
    private final int maxPoolSize = 200;

    // 队列最大长度
    private final int queueCapacity = 1000;

    // 线程池维护线程所允许的空闲时间
    private final int keepAliveSeconds = 300;

    @Bean(name = ThreadPoolConfig.MY_THREAD_POOL_TASK_EXECUTOR)
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        AtomicInteger nextId = new AtomicInteger(1);
        ThreadFactory threadFactory = task -> new Thread(ThreadPoolConfig.systemThreadGroup, task, ThreadPoolConfig.CUSTOM_THREAD + ThreadPoolConfig.STRING + nextId.incrementAndGet() + ThreadPoolConfig.STRING1);
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(maxPoolSize);
        executor.setCorePoolSize(corePoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        // 线程池对拒绝任务(无线程可用)的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setThreadFactory(threadFactory);
        return executor;
    }

    public static void main(String[] args) {

        String str = "管城区";
        String str1 = "管城回族区";
        Integer integer = new Integer(1);
        System.out.println(integer == 1);
        List<String> strings = Arrays.asList("1", "2", "3", "4", "5", "1", "2","6","7","9","10","13","16","19");
        List<String> collect = strings.stream().distinct().collect(Collectors.toList());
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<CompletableFuture<Void>> collect1 = strings.stream().map(e -> CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getId() + "=" + Thread.currentThread().getName());
            System.out.println(e);
        }, executorService)).collect(Collectors.toList());
        List<Void> collect2 = collect1.stream().map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println(collect2);
//        System.out.println(collect);
//        System.out.println(collect.size());
        Random random = new Random();
//        System.out.println(random.nextInt(9));
        List<CompletableFuture<String>> completableFutures = strings.stream().map(e -> CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getId() + "=" + Thread.currentThread().getName());
            return e;
        }, executorService)).collect(Collectors.toList());
        List<String> stringList = completableFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println(stringList);

    }
}
