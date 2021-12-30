package com.xubo.druid.thread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @Author xubo
 * @Date 2021/12/30 13:51
 * 创建线程池的集中方式
 */
public class ThreadTest {

    @Test
    public void createFixedThreadPool() {
        ExecutorService pool = Executors.newFixedThreadPool(2);

    }

    @Test
    public void createCacheThreadPool() {
        ExecutorService pool = Executors.newCachedThreadPool();
    }

    @Test
    public void createScheduleThreadPool() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
    }

    @Test
    public void createSingleThreadPool() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
    }

}
