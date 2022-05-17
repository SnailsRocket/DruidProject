package com.xubo.druid.thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @Author xubo
 * @Date 2022/5/17 13:42
 * 参考博客： https://blog.csdn.net/teachy/article/details/104971814
 * 此方案不适合长时间占用io的操作。
 * 如果有大量请求，请注意控制线程数量，不然可能导致OOM。
 * 实现workFunction时可以自定义线程池而不是用默认的ForkJoinPool（这个线程池根据）
 */
public class FutureTaskWorkerTest {

    @Test
    public void getAllResult() {
        List<Long> list = new ArrayList<>(3);
        list.add(1000L);
        list.add(2000L);
        list.add(3000L);
        // 构造一个异步执行的实体类
        FutureTaskWorker<Long, String> futureTaskWorker = new FutureTaskWorker<>(list, (Long e) -> getThreadName(e));
        long begin = System.currentTimeMillis();
        List<String> allResult = futureTaskWorker.getAllResult();
        // 这个线程就是主线程 mian
        System.out.println("in caculate functio:" + Thread.currentThread().getName());
        long end = System.currentTimeMillis();
        // allResult: [ForkJoinPool.commonPool-worker-2, ForkJoinPool.commonPool-worker-1, ForkJoinPool.commonPool-worker-3] 默认使用的 ForkJoinPool这个线程池
        System.out.println("allResult: " + allResult);
        System.out.println("结束耗时:" + (end - begin));
    }

    private CompletableFuture<String> getThreadName(long sleepTime) {
        // supplyAsync 中使用的是 asyncPool 的 ForkJoinPool 线程池
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 这里面执行的都是线程池里面的线程
            System.out.println(Thread.currentThread().getName() + "已经睡眠" + sleepTime + "毫秒");
            return Thread.currentThread().getName();
        });
    }

}
