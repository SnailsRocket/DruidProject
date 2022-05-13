package com.xubo.druid.hutool;

import cn.hutool.bloomfilter.bitMap.IntMap;
import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.map.MapUtil;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/**
 * @Author xubo
 * @Date 2022/5/12 10:33
 * TimedCache 带过期时间的缓存场景，但是使用redis太重了，毕竟缓存还小
 */
public class TimedCacheUtils {

    private static TimedCache<String, String> timedCache = CacheUtil.newTimedCache(5000);

    static {
        // 每5ms检测一次过期
        timedCache.schedulePrune(5);
    }

    /**
     * 存入键值对，提供消逝时间
     * @param key
     * @param value
     * @param timeout
     */
    public static void put(String key, String value, Long timeout) {
        // 设置消失时间
        timedCache.put(key, value, timeout);
    }

    /**
     * 每次重新get一次缓存，均会重新刷新消逝时间
     * 所以第一次拿到值之后又刷新了过期时间
     * @param key
     * @return
     */
    public static String get(String key) {
        return timedCache.get(key);
    }

    public static void main(String[] args) {

        Integer inte = new Integer(10);
        System.out.println("10".equals(inte));

        put("Druid", "江苏", 3000L);
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("第一次结果：" + get("Druid"));
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("缓存是否存在：" + timedCache.containsKey("Druid"));
        System.out.println("第二次结果：" + get("Druid"));
        try {
            Thread.sleep(2900L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("缓存是否存在：" + timedCache.containsKey("Druid"));
        System.out.println("第三次结果：" + get("Druid"));
        // 取消定时清理
        timedCache.cancelPruneSchedule();
    }
}
