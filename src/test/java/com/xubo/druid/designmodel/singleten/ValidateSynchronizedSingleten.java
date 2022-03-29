package com.xubo.druid.designmodel.singleten;

/**
 * @Author xubo
 * @Date 2022/3/29 15:34
 * 单例模式最优方案
 */
public class ValidateSynchronizedSingleten {

    private volatile static ValidateSynchronizedSingleten instance;

    public ValidateSynchronizedSingleten() {
    }

    public static ValidateSynchronizedSingleten getInstance() {
        if(instance == null) {
            // 双重检查加锁，只有在第一次实例化时，才启动同步机制，提高性能
            synchronized (ValidateSynchronizedSingleten.class) {
                if(instance == null) {
                    instance = new ValidateSynchronizedSingleten();
                }
            }
        }
        return instance;
    }
}
