package com.xubo.druid.javabase;

import org.junit.Test;

/**
 * @Author xubo
 * @Date 2022/5/18 11:03
 * if for 执行中断
 */
public class InterruptTest {

    /**
     * if Interrupt
     * for 同理
     */
    @Test
    public void ifInterrupt() {
        Integer i = new Integer(10);
        String a = "123";
        tryComplete: if(i.equals(Integer.valueOf(10))) {
            if(a.equals("123")) {
                System.out.println("exit");
                // 执行到这个地方退出if循环
                break tryComplete;
            }
            System.out.println("a = " + a);
        }
    }


}
