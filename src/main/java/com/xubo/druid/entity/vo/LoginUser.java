package com.xubo.druid.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author xubo
 * @Date 2022/5/17 17:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginUser implements Serializable {

    private Integer id;

    private String personKey;

    private String nickName;

    private String sex;

    private String description;

    private String image;

    /**
     * 浏览器是否已有user-key
     */
    private Boolean tempUser = false;

}
