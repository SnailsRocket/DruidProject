package com.xubo.druid.hutool;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.thread.ThreadUtil;
import com.google.common.util.concurrent.*;

import javax.annotation.Nullable;
import java.text.MessageFormat;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/**
 * @Author xubo
 * @Date 2022/5/12 11:01
 * TimedCache 带过期时间的缓存场景
 * 设置监听
 */
public class TimedCacheProUtils {

    private static TimedCache<String, String> timedCache = CacheUtil.newTimedCache(5000);

    // 线程池
    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    private static final ListeningExecutorService listeningExecutorService =
            MoreExecutors.listeningDecorator(executorService);

    // 回调方法映射
    private static ConcurrentHashMap<String, Consumer<String>> callBackMap;

    static {
        // 每5ms检测一次过期
        timedCache.schedulePrune(5);
    }

    /**
     * 存入键值对，提供消逝时间
     *
     * @param key
     * @param value
     * @param timeout
     */
    public static void put(String key, String value, Long timeout, Consumer<String> consumer) {
        // 设置消失时间
        timedCache.put(key, value, timeout);
        addListen(key, consumer);
    }

    /**
     * 每次重新get一次缓存，均会重新刷新消逝时间
     * 所以第一次拿到值之后又刷新了过期时间
     *
     * @param key
     * @return
     */
    public static String get(String key) {
        return timedCache.get(key);
    }

    /**
     * 删除缓存和回调映射
     *
     * @param key
     */
    public static void remove(String key) {
        callBackMap.remove(key);
        timedCache.remove(key);
    }

    /**
     * 添加监听器
     *
     * @param key
     * @param consumer
     */
    public static void addListen(String key, Consumer<String> consumer) {
        ListenableFuture<String> listenableFuture = listeningExecutorService.submit(() -> {
            while (timedCache.containsKey(key)) {
                ThreadUtil.sleep(500);
            }
            return key;
        });
        Futures.addCallback(
                listenableFuture,
                new FutureCallback<String>() {

                    @Override
                    public void onSuccess(@Nullable String s) {
                        consumer.accept(s);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                },
                listeningExecutorService);
    }

    public static void main(String[] args) {
        put("Druid", "江苏", 3000L,x -> System.out.println(MessageFormat.format("[{0}] - 缓存消逝", x)));
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
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("缓存是否存在：" + timedCache.containsKey("Druid"));
        System.out.println("第三次结果：" + get("Druid"));
        // 取消定时清理
        listeningExecutorService.shutdown();
        timedCache.cancelPruneSchedule();
    }

}
