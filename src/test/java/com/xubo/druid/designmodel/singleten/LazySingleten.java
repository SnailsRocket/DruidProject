package com.xubo.druid.designmodel.singleten;


/**
 * @Author xubo
 * @Date 2022/3/25 17:16
 * @Desc 单例模式-懒汉式
 * 下面这种方式不安全 如果两个线程同时在获取单例对象，线程1执行到19行 判断 instance 是否为null后，线程调度机制将cpu资源分配给线程2，此时
 * 线程2执行第19行 判断 instance 是否为null时，也发现单例类还没有实例化，这样就导致实例化两次，为了防止这种情况
 */
public class LazySingleten {

    // 类初始化时，不初始化这个对象(延时加载，真正用的时候再创建)
    private static LazySingleten instance;

    private LazySingleten() {}

    // 方法同步，调用效率低 每次获取单例对象都会加锁，这样带来性能损失，可以将 synchronized 关键字放入里面
    public static synchronized LazySingleten getInstance() {
        if(instance == null) {
            instance = new LazySingleten();
        }
        return instance;
    }
}
