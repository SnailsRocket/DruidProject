package com.xubo.druid.entity.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.xubo.druid.handler.BarTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 * @TableName foo_bar
 */
@TableName(value ="foo_bar")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FooBar implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private Integer age;

    /**
     * 
     */
    @TableField(typeHandler = BarTypeHandler.class)
    private Bar bar;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}