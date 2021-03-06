package com.xubo.druid.entity.pay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author xubo
 * @Date 2022/2/7 11:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginNotify {

    public String partner;

    public String userId;

    public String mobile;

    public Date createTime;

    public String data;

}
