package com.xubo.druid.thread;

import com.sun.corba.se.impl.orbutil.threadpool.WorkQueueImpl;
import com.sun.corba.se.spi.orbutil.threadpool.WorkQueue;
import org.apache.commons.lang.time.DateFormatUtils;
import org.junit.Test;
import org.springframework.util.DigestUtils;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.*;

/**
 * @Author xubo
 * @Date 2021/12/30 13:51
 * 创建线程池的集中方式
 */
public class ThreadTest {

    @Test
    public void createFixedThreadPool() {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程" + Thread.currentThread().getName());
                }
            });
        }

    }

    @Test
    public void createCacheThreadPool() {
        ExecutorService pool = Executors.newCachedThreadPool();
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程" + Thread.currentThread().getName());
            }
        });
    }

    @Test
    public void createScheduleThreadPool() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
    }

    @Test
    public void createSingleThreadPool() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
    }

    @Test
    public void newFixedThreadPool() {
        int nThreads = 10;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    }

    @Test
    public void testMd5() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        // java自带工具包MessageDigest
        String resultString = MD5Utils.stringToMD5("123456");
        System.out.println(resultString);
        // e10adc3949ba59abbe56e057f20f883e
        String resultString1 = MD5Utils.stringToMD5("1234");
        System.out.println(resultString1);
        //81dc9bdb52d04dc20036dbd8313ed055

        // spring自带工具包DigestUtils
        System.out.println(DigestUtils.md5DigestAsHex("1234".getBytes()));
        System.out.println(DigestUtils.md5Digest("81dc9bdb52d04dc20036dbd8313ed055".getBytes()));
        // 81dc9bdb52d04dc20036dbd8313ed055

        long dataState = 1491951454819266561l;
        String format = DateFormatUtils.format(dataState, "yyyy-MM-dd HH:mm:ss");

        System.out.println(format);
    }

    @Test
    public void testSet() {
        Set<String> strSet = new HashSet<>();
        strSet.add("abaa");
        strSet.add("aabb");
        strSet.add("bbaa");
        strSet.add("ddbb");
        Set<String> treeSet = new TreeSet<>(strSet);
        System.out.println(strSet);
        System.out.println(treeSet);
        String str = "04F8B7F05CB4A27504E43B484D9B8F53"; // 04F8B7F05CB4A27504E43B484D9B8F53
        System.out.println(str.length());
        byte[] bytes = DigestUtils.md5Digest(str.getBytes());
        String s = new String(bytes);
        System.out.println(s);
        BigDecimal ten = BigDecimal.TEN;
        String s1 = String.valueOf(ten);
        System.out.println(s1);

    }

}
