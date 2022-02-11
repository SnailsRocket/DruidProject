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

    private String partner;

    private String userId;

    private String mobile;

    private Date createTime;

    private String data;

}
