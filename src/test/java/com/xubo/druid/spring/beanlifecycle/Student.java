package com.xubo.druid.spring.beanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

import java.util.Objects;

/**
 * @Author xubo
 * @Date 2022/1/5 13:49
 * Spring Bean 的生命周期
 * bean自身的方法  cuetomeInit  CustomDestory
 * bean 级生命周期接口的方法  实现了 BeanFactoryAware  BeanNameAware  InitializingBean  DisposableBean 的方法
 */
public class Student implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {
    private String name;
    private int age;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student() {
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryAware 的 setBeanFactory方法");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("BeanNameAware 的 setBeanName 方法 传入的参数 beanName : " + s);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposiableBean 的 destory方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean的实现afterPropertiesSet 能否获取到属性：" + this.getName() + " - " + this.getAge());
    }

    public void customeInit(){
        System.out.println("Student : 自定义的初始化方法");
    }

    public void customDestroy(){
        System.out.println("Student : 自定义的销毁方法");
    }
}
