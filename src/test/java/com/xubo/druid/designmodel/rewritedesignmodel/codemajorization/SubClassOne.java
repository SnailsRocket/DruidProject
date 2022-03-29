package com.xubo.druid.designmodel.rewritedesignmodel.codemajorization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author xubo
 * @Date 2022/3/29 14:31
 */
public class SubClassOne extends ParentClass {

    private static Logger log = LoggerFactory.getLogger(SubClassOne.class);

    @Override
    public void commonMethod() {
        super.commonMethod();
    }

    @Override
    public void differenceMethod() {
        log.info("子类one打印差异方法！");
    }
}
