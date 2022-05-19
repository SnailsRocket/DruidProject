package com.xubo.druid.spring.beanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

/**
 * @Author xubo
 * @Date 2022/1/5 16:19
 * 服务划分
 *  订单服务
 *  支付服务
 *  商品服务
 *  用户服务
 *  积分服务 (这个可以集成到用户服务)
 *  认证服务 Oauth2/ SpringSecuroty
 *  搜索服务
 *  物流服务
 *  首页
 *  消息服务(发送MQ、短信、邮件) 参考Austin
 *  秒杀服务
 *  购物车服务(有必要吗?)
 *  营销服务(配置活动)
 *
 *
 */
public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {
    public MyInstantiationAwareBeanPostProcessor() {
        super();
        System.out.println("InstantiationAwareBeanPostProcessorAdapter 实现类构造器");
    }

    //实例化bean之前调用
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessorAdapter before instantiation");
        return super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    //实例化bean之后调用
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessorAdapter after instantiation");
        return super.postProcessAfterInstantiation(bean, beanName);
    }


    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessorAdapter properties :" + pvs.toString());
        return super.postProcessProperties(pvs, bean, beanName);
    }
}
