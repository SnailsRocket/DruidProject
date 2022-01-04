package com.xubo.druid.thread;

import com.sun.corba.se.impl.orbutil.threadpool.WorkQueueImpl;
import com.sun.corba.se.spi.orbutil.threadpool.WorkQueue;
import org.junit.Test;

import java.util.concurrent.*;

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

    @Test
    public void newFixedThreadPool(int nThreads) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    }

}
