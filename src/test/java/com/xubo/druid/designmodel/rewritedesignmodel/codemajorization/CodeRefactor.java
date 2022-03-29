package com.xubo.druid.designmodel.rewritedesignmodel.codemajorization;

/**
 * @Author xubo
 * @Date 2022/3/29 14:36
 * 实体类很长建议重构，共性方法&完全差异方法抽取到父类中，子类去继承&覆盖方法
 */
public class CodeRefactor {

    public static void main(String[] args) {
        ParentClass parentClass = new ParentClass();
        SubClassOne subClassOne = new SubClassOne();
        SubClassTwo subClassTwo = new SubClassTwo();
        subClassOne.commonMethod();
        subClassOne.differenceMethod();
        System.out.println("-------");
        subClassTwo.commonMethod();
        subClassTwo.differenceMethod();
        System.out.println("-------");
        parentClass.commonMethod();
        parentClass.differenceMethod();
    }

}
