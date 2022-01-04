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
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程" + Thread.currentThread().getName());
                }
            });
        }

    }

    @Test
    public void createCacheThreadPool() {
        ExecutorService pool = Executors.newCachedThreadPool();
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程" + Thread.currentThread().getName());
            }
        });
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
    public void newFixedThreadPool() {
        int nThreads = 10;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    }

}
