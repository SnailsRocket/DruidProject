package com.xubo.druid.leetcode.juc.orderprint;

import java.util.concurrent.locks.Lock;

/**
 * @Author xubo
 * @Date 2022/1/18 17:48
 *  plan 2
 */
public class Foo1 {

    private boolean firstFinished;
    private boolean secondFinished;
    private Object lock = new Object();

    public Foo1() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (lock) {
            printFirst.run();
            firstFinished = true;
            lock.notifyAll();
        }

    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (lock) {
            while(!firstFinished) {
                lock.wait();
            }

            printSecond.run();
            secondFinished = true;
            lock.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (lock) {
            while(!secondFinished) {
                lock.wait();
            }

            printThird.run();
        }

    }
}
