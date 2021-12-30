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
 * @TableName score
 */
@TableName(value ="score")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Score implements Serializable {
    /**
     * 
     */
    @TableId
    private String sid;

    /**
     * 
     */
    @TableId
    private String cid;

    /**
     * 
     */
    private Integer sscore;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}