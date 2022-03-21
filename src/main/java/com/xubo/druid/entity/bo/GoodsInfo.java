package com.xubo.druid.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author xubo
 * @Date 2022/3/21 11:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodsInfo {

    public Integer id;

    public String name;

    public String description;

    public BigDecimal initialPrice;

    public BigDecimal discountPrice;

    public BigDecimal remainPrice;

}
