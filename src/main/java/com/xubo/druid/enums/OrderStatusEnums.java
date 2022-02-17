package com.xubo.druid.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @Author xubo
 * @Date 2022/2/17 16:15
 */
@Getter
public enum OrderStatusEnums {

    PRE_PAY(1, 0, "待付款"),
    PRE_DELIVERY(2, 1, "待发货"),
    CANCEL(3, 2, "已取消"),
    FINISHED(4, 3, "已完成"),
    RETURN_GOODS(5, 4, "退货"),
    TIME_OUT_CANCEL(6, 5, "超时自动取消");

    private Integer id;
    private Integer code;
    private String desc;

    OrderStatusEnums(Integer id, Integer code, String desc) {
        this.id = id;
        this.code = code;
        this.desc = desc;
    }

    OrderStatusEnums() {
    }

    public static String getStringByCode(Integer code) {
        if(code == null) return "";
        return Arrays.asList(OrderStatusEnums.values()).stream().filter(e -> e.getCode().equals(code)).findFirst().get().getDesc();
    }
}
