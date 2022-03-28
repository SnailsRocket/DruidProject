package com.xubo.druid.designmodel.singleten;

/**
 * @Author xubo
 * @Date 2022/3/25 17:16
 * @Desc 单例模式-饿汉式
 *
 */
public class HungeySingleten {

    private static HungeySingleten instance = new HungeySingleten();

    private HungeySingleten() {}

    public static synchronized HungeySingleten getInstance() {
        return instance;
    }
}
