package com.xubo.druid.test;

import com.xubo.druid.entity.pay.Order;
import com.xubo.druid.enums.OrderStatusEnums;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * @Author xubo
 * @Date 2022/2/17 16:32
 */
public class OrderStateTest {

    @Test
    public void matchOrderState() {
        Order order = Order.builder().id(1)
                .orderId("123456")
                .orderDesc("测试订单")
                .orderState(1)
                .CreateTime(LocalDateTime.now())
                .build();
        String orderDesc = OrderStatusEnums.getStringByCode(order.getOrderState());
        System.out.println(String.valueOf(System.currentTimeMillis()).substring(4, 10));
        System.out.println("1645087702161".length());
        String str = "金龙鱼 芝麻香油 芝麻调和香油火锅调味油 145ml";
        System.out.println(str.length());
        String str1 = "https://mgb.cn/shop_jz/jz/pay/payNotifyttps://mgb.cn/shop_jz/#/pages/myOrder/myOrderhttp://14.21.46.171:18082/file/23fbf501-83c4-4bf8-8b53-f0b9449ad764.jpgYDMGBSC202202161402110008192.168.2.96";
        System.out.println("str1 : " + str1.length());
        System.out.println(str.length() > 25 ? str.substring(0,25).length() : str);
    }


}
