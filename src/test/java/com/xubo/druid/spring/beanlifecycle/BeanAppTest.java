package com.xubo.druid.spring.beanlifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author xubo
 * @Date 2022/1/5 16:25
 * 参考博客  https://blog.csdn.net/csdn_wangchen/article/details/86704018?spm=1001.2014.3001.5501
 *  https://blog.csdn.net/qq_35634181/article/details/104473308?spm=1001.2101.3001.6650.5&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EOPENSEARCH%7Edefault-5.no_search_link&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EOPENSEARCH%7Edefault-5.no_search_link&utm_relevant_index=10
 */
public class BeanAppTest {

    public static void main(String[] args) {

        System.out.println("开始初始化容器");
        ApplicationContext context = new AnnotationConfigApplicationContext(StudentConfig.class);
        System.out.println("容器初始化完毕");
        Student student = (Student) context.getBean("student");
        System.out.println(student.getName());
        System.out.println("开始关闭容器");
        ((AnnotationConfigApplicationContext)context).registerShutdownHook();

    }

}
