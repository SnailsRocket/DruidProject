package com.xubo.druid.service.impl;

import com.xubo.druid.entity.bo.GoodsInfo;
import com.xubo.druid.entity.domain.FooBar;
import com.xubo.druid.entity.domain.Student;
import com.xubo.druid.service.BuilderService;

import java.math.BigDecimal;

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


    public static void main(String[] args) {
        Integer in = Integer.valueOf(10);
        System.out.println("in.equals(null) = " + in + null);
        in = null;
        System.out.println("i = " + in);

        Integer a = Integer.valueOf(10);
        Integer b = Integer.valueOf(10);
        Integer c = Integer.valueOf(12);
        System.out.println(a.compareTo(c));
        BigDecimal bigDecimal = new BigDecimal(10);

        GoodsInfo goodsInfo = new GoodsInfo();
        int i = goodsInfo.getId() * 10;
        System.out.println(i);
        BigDecimal multiply = goodsInfo.getInitialPrice().multiply(bigDecimal);
        System.out.println(multiply);


    }

}
