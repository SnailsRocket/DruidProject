package com.xubo.druid.spring.beanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @Author xubo
 * @Date 2022/1/5 13:58
 * Created by wangchen on 2019/1/30.
 * 容器级生命周期接口方法  后处理器
 */
public class CustomBeanPostProcessor implements BeanPostProcessor {

    public CustomBeanPostProcessor() {
        System.out.println("BeanPostProcessor 实现类构造器");
    }

    //实例化之前
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.print("BeanPostProcessor实现类 Before Initialization   =====  ");
        System.out.println("两个参数：bean beanName ：" + bean + "--" + beanName);
        return bean;
    }

    //实例化之后
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.print("实现了BeanPostProcessor After Initialization  =====  ");
        System.out.println("两个参数：bean beanName ：" + bean + "--" + beanName);
        return bean;
    }

}
