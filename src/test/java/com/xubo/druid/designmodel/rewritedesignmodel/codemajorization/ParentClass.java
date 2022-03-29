package com.xubo.druid.designmodel.rewritedesignmodel.codemajorization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author xubo
 * @Date 2022/3/29 14:25
 * 父类中放共性的模板方法  和 完全不同的方法  使用继承 和 覆盖
 */
public class ParentClass {

    private final Logger log = LoggerFactory.getLogger(ParentClass.class);

    public void commonMethod() {
        log.info("打印父类公共方法日志！");
    }

    public void differenceMethod() {
        log.info("打印父类差异方法日志！");
    }

}
