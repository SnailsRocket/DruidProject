package com.xubo.druid.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author xubo
 * @Date 2022/1/10 14:05
 */
public class ArrayListTest {

    /**
     * 对 List 集合进行操作 addAll()  removeAll() 这两个方法会报 UnsupportedOperationException ArrayList对这两个方法进行了重写，所以运行不会出异常
     *
     */
    @Test
    public void testList() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 6, 9, 10);
        List<Integer> list1 = Arrays.asList(1, 2, 3, 5, 8, 9, 11);
        // 交集
        List<Integer> collect = list.stream().filter(e -> list1.contains(e)).collect(Collectors.toList());
        System.out.println(collect);

        // 并集  list 是 List接口，没有实现 addAll 和removeAll方法，所以不支持，运行会抛异常
        list.addAll(list1);
        List<Integer> list3 = new ArrayList<>();
        list3.addAll(list);
        list1.removeAll(collect);
        list3.addAll(list1);
        System.out.println(list3);
    }
}
