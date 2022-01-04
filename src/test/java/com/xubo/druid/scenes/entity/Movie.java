package com.xubo.druid.scenes.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author xubo
 * @Date 2022/1/4 17:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {

    public static final int NEW_RELEASE = 1;
    public static final int REGULAR = 0;
    public static final int CHILDRENS = 2;

    private String title;
    private int priceCode;
}
