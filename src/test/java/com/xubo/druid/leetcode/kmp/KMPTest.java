package com.xubo.druid.leetcode.kmp;

import org.junit.Test;

/**
 * @Author xubo
 * @Date 2022/2/21 16:47
 *
 * KMP 算法
 * 详解 ： https://www.cnblogs.com/yjiyjige/p/3263858.html
 *
 * 题目： 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。
 *       如果不存在，则返回  -1 。
 * https://leetcode-cn.com/problems/implement-strstr
 *
 * haystack = "hello", needle = "ll"
 * 输出：2
 *
 * haystack = "aaaaa", needle = "bba"
 * 输出：-1
 *
 * haystack = "", needle = ""
 * 输出：0
 *
 */
public class KMPTest {

    /**
     * 暴力破解 获取子串的索引
     *
     */
    @Test
    public void findSubStringIndex() {
        String str = "hello";
        String subString = "ll";
        char[] strChar = str.toCharArray();
        char[] subStringChar = subString.toCharArray();
        int i = 0;
        int j = 0;
        while(i < strChar.length && j < subStringChar.length) {
            if(strChar[i] == subStringChar[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        if(j == subStringChar.length) {
            System.out.println(i - j);
        } else {
            System.out.println(-1);
        }
    }
}
