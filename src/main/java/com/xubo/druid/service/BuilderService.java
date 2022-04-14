package com.xubo.druid.service;

import com.xubo.druid.entity.domain.Student;

/**
 * @Author xubo
 * @Date 2022/4/14 13:21
 */
public interface BuilderService {

    public BuilderService processOne();

    public BuilderService processTwo();

    public BuilderService processThree();

    public Student checkded();

    public Student build();
}
