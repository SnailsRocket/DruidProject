package com.xubo.druid.javabase;

import org.junit.Test;

/**
 * @Author xubo
 * @Date 2022/2/17 11:56
 */
public class IntegerTest {

    /**
     * 根据源码分析
     * if (i >= IntegerCache.low && i <= IntegerCache.high)
     *             return IntegerCache.cache[i + (-IntegerCache.low)];
     *  数字在 -128~127之间 就直接使用cache 数组中的数据 ，如果不在这个范围内就 new Integer
     */
    @Test
    public void integerTest() {
        Integer integer = Integer.valueOf(String.valueOf(0001));
        System.out.println(integer);

        Integer i1 = 59;                        // 进行了一个装箱操作
        int i2 = 59;
        Integer i3 = Integer.valueOf(59);       // 返回的是Cache数组上的引用
        Integer i4 = new Integer(59);    // 在堆上面创建对象
        System.out.println("-----------");
        System.out.println(i1 == i2); // false      true   右边是整型，所以左边进行拆箱操作，进行比较
        System.out.println(i1 == i3); // false      true
        System.out.println(i1 == i4); // true       false
        System.out.println(i2 == i3); // false      true
        System.out.println(i2 == i4); // false      true    这里又涉及到拆箱 i4进行了拆箱操作
        System.out.println(i3 == i4); // false      false

    }


}
