package com.xubo.druid.service.impl;

import com.xubo.druid.entity.domain.Student;
import com.xubo.druid.service.BuilderService;

/**
 * @Author xubo
 * @Date 2022/4/14 13:22
 */
public class BuilderServiceImpl implements BuilderService {


    @Override
    public BuilderService processOne() {
        System.out.println("processOne() = 1");
        return this;
    }

    @Override
    public BuilderService processTwo() {
        System.out.println("processTwo() = 2");
        return this;
    }

    @Override
    public BuilderService processThree() {
        System.out.println("processThree() = 3");
        return this;
    }

    @Override
    public Student checkded() {
        return new Student("12", "Druid", "11.09", "男");
    }

    @Override
    public Student build() {
        return new Student("11", "xu", "11.20","男");
    }
}
