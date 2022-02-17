package com.xubo.druid.entity.pay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author xubo
 * @Date 2022/2/17 16:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    public Integer id;

    public String orderId;

    public String orderDesc;

    public Integer orderState;

    public LocalDateTime CreateTime;

}
