package com.xubo.druid.scenes.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author xubo
 * @Date 2022/1/4 17:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rental {

    //表示某客户租了一步影片
        private Movie movie;
        private int daysRented;

}
