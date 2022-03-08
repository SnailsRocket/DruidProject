package com.xubo.druid.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

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
}
