package com.xubo.druid.leetcode.juc.orderprint;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author xubo
 * @Date 2022/1/19 9:23
 */
public class OrderPrintTest {

    /*public static void main(String[] args) {

        Integer[] arrs = {1,2,3};
        ExecutorService pool = Executors.newFixedThreadPool(3);
        pool.execute(new Runnable() {
            @Override
            public void run() {

            }
        });

    }*/


    @Test
    public void testPrint() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程开始执行！");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程2开始执行！");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程3开始执行！");
            }
        }).start();
    }

    /**
     * main1
     * -----------------
     * Thread-013
     * Thread-114
     * 会打印主线程  main
     * 主线程会调用 task.run()，然后是其他两个创建的线程调用 task
     */
    @Test
    public void testPrintThread() {

        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + Thread.currentThread().getId());
            }
        };
        task.run();

        Thread thread = new Thread(task);
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------");
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + Thread.currentThread().getId());
            }
        }).start();
    }

}
