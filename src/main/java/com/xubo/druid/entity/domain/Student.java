package com.xubo.druid.entity.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName student
 */
@TableName(value ="student")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student implements Serializable {
    /**
     * 
     */
    @TableId
    private String sid;

    /**
     * 
     */
    private String sname;

    /**
     * 
     */
    private String sbirth;

    /**
     * 
     */
    private String ssex;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}