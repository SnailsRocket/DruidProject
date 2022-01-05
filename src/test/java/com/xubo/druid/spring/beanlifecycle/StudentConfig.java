package com.xubo.druid.spring.beanlifecycle;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.annotation.Bean;

/**
 * @Author xubo
 * @Date 2022/1/5 16:22
 */
@Configurable
public class StudentConfig {

    @Bean
    public BeanPostProcessor beanPostProcessor(){
        return new CustomBeanPostProcessor();
    }

    @Bean
    public InstantiationAwareBeanPostProcessor instantiationAwareBeanPostProcessor(){
        return new MyInstantiationAwareBeanPostProcessor();
    }

    @Bean
    public BeanFactoryPostProcessor beanFactoryPostProcessor(){
        return new MyBeanFactoryPostProcessor();
    }

    @Bean(initMethod = "customeInit",destroyMethod = "customDestroy")
    public Student student(){
        return new Student("王晨",23);
    }

}
