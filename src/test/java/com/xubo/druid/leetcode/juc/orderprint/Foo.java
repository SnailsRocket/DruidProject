package com.xubo.druid.leetcode.juc.orderprint;

/**
 * @Author xubo
 * @Date 2022/1/18 17:43
 * 多线程 按顺序打印
 * leetcode    https://leetcode-cn.com/problems/print-in-order/
 * 题解        https://blog.csdn.net/Dream_Weave/article/details/122476116?spm=1001.2014.3001.5501
 * plan 1
 */
public class Foo {

    public Foo() {
    }

    volatile int count = 1;

    public void first(Runnable printFirst) {
        printFirst.run();
        count++;
    }

    public void second(Runnable printSecond) {
        while(count != 2);
        printSecond.run();
        count++;
    }

    public void third(Runnable printThird) {
        while(count != 3);
        printThird.run();
    }
}
