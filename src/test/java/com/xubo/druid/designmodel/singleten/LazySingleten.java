package com.xubo.druid.designmodel.singleten;


/**
 * @Author xubo
 * @Date 2022/3/25 17:16
 * @Desc 单例模式-懒汉式
 */
public class LazySingleten {

    // 类初始化时，不初始化这个对象(延时加载，真正用的时候再创建)
    private static LazySingleten instance;

    private LazySingleten() {}

    // 方法同步，调用效率低
    public static synchronized LazySingleten getInstance() {
        if(instance == null) {
            instance = new LazySingleten();
        }
        return instance;
    }
}
