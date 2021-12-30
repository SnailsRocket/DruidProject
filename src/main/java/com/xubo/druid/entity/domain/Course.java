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
 * @TableName course
 */
@TableName(value ="course")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course implements Serializable {
    /**
     * 
     */
    @TableId
    private String cid;

    /**
     * 
     */
    private String cname;

    /**
     * 
     */
    private String tid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}