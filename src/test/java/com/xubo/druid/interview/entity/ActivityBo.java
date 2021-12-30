package com.xubo.druid.interview.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author xubo
 * @Date 2021/12/30 11:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityBo {

    private String activity;

    private String actStartDate;

}
