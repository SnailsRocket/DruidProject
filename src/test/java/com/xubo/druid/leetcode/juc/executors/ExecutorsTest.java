package com.xubo.druid.leetcode.juc.executors;

import cn.hutool.json.JSONUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @Author xubo
 * @Date 2022/1/19 14:05
 * Callable  跟 Runnable 的区别
 */
public class ExecutorsTest {

    @Test
    public void testSingleThread() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit( () -> {
            System.out.println(Thread.currentThread().getName());
        });
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("isShutdown " + executorService.isShutdown());
        System.out.println("isTerminated " + executorService.isTerminated());
        // submit 跟 execute 的区别
        executorService.execute(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        executorService.shutdownNow();
        System.out.println("isShutdown" + executorService.isShutdown());
    }

    /**
     * future.get() 会调用阻塞， 所以可以用 future..get(1, TimeUnit.MINUTES) 1分钟后调用阻塞
     */
    @Test
    public void testFixedThread() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<Integer> submitResult = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                TimeUnit.SECONDS.sleep(10);
                System.out.println(Thread.currentThread().getName());
                return 123;
            }
        });

        System.out.println("isDone " + submitResult.isDone());
        System.out.println("isCancelled " + submitResult.isCancelled());
        try {
            System.out.println(submitResult.get());
            try {
                System.out.println(submitResult.get(1, TimeUnit.SECONDS));
            } catch (TimeoutException e) {
                e.printStackTrace();
                System.out.println(e);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("isDone " + submitResult.isDone());
        System.out.println("isCancelled " + submitResult.isCancelled());
    }

    /**
     * invokeAll一次批量提交多个 callable
     */
    @Test
    public void testCallable() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Callable<String>> callableList = Arrays.asList(
                () -> "task1",
                () -> "task2",
                () -> "task3",
                () -> "task4"
        );

        List<Future<String>> futureList = new ArrayList<>();
        try {
            futureList = executorService.invokeAll(callableList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        futureList.stream().map(future -> {
            try {
                return future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return null;
        }).forEach(System.out::println);

    }

    @Test
    public void testInvokeAll() {
        Arrays.asList(
                callable("task1", 1),
                callable("task2", 2)
        );
    }

    public String callable(String str, Integer id) {
        return str;
    }

    /**
     * newWorkStealingPool 返回一个 ForkJoinPool 的线程池
     * ForkJoinPool 默认主机CPU的可用核心数作为线程数
     *
     */
    @Test
    public void testForkJoinPool() {
        ExecutorService executorService = Executors.newWorkStealingPool();
        System.out.println(Runtime.getRuntime().availableProcessors());

    }

    @Test
    public void testScheduledThread() throws InterruptedException{
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        Runnable task = () -> System.out.println("Scheduling" + System.nanoTime());

        ScheduledFuture<?> future = executorService.schedule(task, 3, TimeUnit.MILLISECONDS);
        TimeUnit.MILLISECONDS.sleep(1337);

        long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);
        System.out.printf("Remaining Delay: %sms", remainingDelay);

    }

}
