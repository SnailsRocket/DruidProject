package com.xubo.druid.objectmapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @Author xubo
 * @Date 2022/1/13 11:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo {

    private Integer id;

    private String name;

    private String address;

    private List<String> tels;

    private Date time;

}
