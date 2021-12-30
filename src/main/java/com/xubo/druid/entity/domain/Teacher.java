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
 * @TableName teacher
 */
@TableName(value ="teacher")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teacher implements Serializable {
    /**
     * 
     */
    @TableId
    private String tid;

    /**
     * 
     */
    private String tname;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}