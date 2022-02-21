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
        String str1 = "trans_channel=mb&subject=联创 不锈钢双层便当盒 DF-CP0616M&input_charset=utf-8&fee_type=1&body=饭盒采用食品级原料和优质不锈钢精制而成，卫生安全；顶盖配有橡胶密封圈，盒盖采用加长扣合设计，使盒盖与&notify_url=https://mgb.cn/shop_jz/jz/pay/payNotify&spbill_create_ip=192.168.2.96&out_trade_no=YDMGBSC202202161402110008&partner=120105278&service=pay_service&total_fee=34.0&trade_mode=0002&return_url=https://mgb.cn/shop_jz/#/pages/myOrder/myOrder&show_url=http://14.21.46.171:18082/file/23fbf501-83c4-4bf8-8b53-f0b9449ad764.jpg&sign=8aa8797e0a41044a61ab4db3553f6e5e";
        System.out.println("str1 : " + str1.length());
        System.out.println(str.length() > 25 ? str.substring(0,25).length() : str);
    }


}
