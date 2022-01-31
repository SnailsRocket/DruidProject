package com.xubo.druid.util.convertlist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author xubo
 * @Date 2022/1/25 10:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DateConvertSource {

    private String name;

    private Integer age;

    private String address;

    private String tel;

    private String description;

}
