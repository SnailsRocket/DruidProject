package com.xubo.druid.entity.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author xubo
 * @Date 2022/1/12 16:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bar {

    private Integer id;

    private String tel;

    private String address;

}
