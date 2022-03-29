package com.xubo.druid.designmodel.rewritedesignmodel.codemajorization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author xubo
 * @Date 2022/3/29 14:33
 */
public class SubClassTwo extends ParentClass{

    private static Logger log = LoggerFactory.getLogger(SubClassTwo.class);

    @Override
    public void commonMethod() {
        super.commonMethod();
    }

    @Override
    public void differenceMethod() {
        log.info("子类two差异性方法！");
    }
}
