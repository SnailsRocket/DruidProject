package com.xubo.druid.designmodel.singleten;

import org.springframework.util.StringUtils;

/**
 * @Author xubo
 * @Date 2022/3/29 15:02
 */
public class SynchronizedLazySingleten {

    private static SynchronizedLazySingleten instence;

    public static SynchronizedLazySingleten getInstence() {
        if (StringUtils.isEmpty(instence)) {
            synchronized (SynchronizedLazySingleten.class) {
                instence = new SynchronizedLazySingleten();
            }
        }
        return instence;
    }
}
