package com.xubo.druid.leetcode.kmp;

import com.xubo.druid.entity.domain.Score;
import com.xubo.druid.entity.domain.Student;
import org.junit.Test;
import org.springframework.util.DigestUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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

























    @Test
    public void findStrIndex() {
        String t = "hello";
        String p = "ll";
        char[] tChars = t.toCharArray();
        char[] pChars = p.toCharArray();
        int i = 0;
        int j = 0;
        while(i < tChars.length && j < pChars.length) {
            if(tChars[i] == pChars[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        if(j == pChars.length) {
            System.out.println(i - j);
        } else {
            System.out.println(-1);
        }
    }

    /**
     * kmp 算法
     */
    @Test
    public void testKMP() {
        String p = "ll";
        char[] pChars = p.toCharArray();
        int[] next = new int[pChars.length];
        next[0] = -1;
        int j = 0;
        int k = -1;
        while (j < pChars.length - 1) {
            if(k == -1 || pChars[j] == pChars[k]) {
                next[j++] = ++k;
            } else {
                k = next[k];
            }
        }
        System.out.println(next[0] + " : " + next[1]);
    }

    @Test
    public void bigDecimal() {
        List<Score> scoreList = Arrays.asList(Score.builder().sid("1").sscore(20).build(),
                Score.builder().sid("2").sscore(10).build(),
                Score.builder().sid("3").sscore(30).build(),
                Score.builder().sid("4").sscore(40).build(),
                Score.builder().sid("5").sscore(50).build(),
                Score.builder().sid("6").sscore(25).build()
        );

        Score score = scoreList.stream().max(Comparator.comparing(e -> e.getSscore())).get();
        System.out.println(score);

    }

}
