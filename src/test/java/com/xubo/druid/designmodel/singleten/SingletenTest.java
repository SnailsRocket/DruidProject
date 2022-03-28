package com.xubo.druid.designmodel.singleten;

import com.xubo.druid.spring.beanlifecycle.Student;
import org.junit.Test;

/**
 * @Author xubo
 * @Date 2022/3/25 17:12
 */
public class SingletenTest {

    /**
     * 用时创建
     */
    @Test
    public Student lazySingleten() {
         Student student = null;


        return student;
    }

    /**
     * 先创建
     */
    @Test
    public void hungrySingleten() {


    }




}
