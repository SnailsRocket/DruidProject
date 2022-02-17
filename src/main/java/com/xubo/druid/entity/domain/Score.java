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
    @TableField("s_id")
    private String sid;

    /**
     * 
     */
    @TableField("c_id")
    private String cid;

    /**
     * 
     */
    @TableField("s_score")
    private Integer sscore;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}