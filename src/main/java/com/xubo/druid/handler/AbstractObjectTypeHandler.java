package com.xubo.druid.handler;

import cn.hutool.json.JSONUtil;
import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;

/**
 * git
 * @Author xubo
 * @Date 2022/1/11 15:50
 * Mybatis 优雅的缓存 json https://blog.csdn.net/qq271859852/article/details/103330914
 * 自定义实体类 类型转换
 * 继承 BaseTypeHandler 这个抽象类  实现下面四个方法
 *
 */
public abstract class AbstractObjectTypeHandler<T> extends BaseTypeHandler<T> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, T params, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, this.toJson(params));
    }

    @Override
    public T getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        String json = resultSet.getString(columnName);
        return StringUtils.isBlank(json) ? null : this.parse(json);
    }

    @Override
    public T getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        String json = resultSet.getString(columnIndex);
        return StringUtils.isBlank(json) ? null : this.parse(json);
    }

    @Override
    public T getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        String json = callableStatement.getString(columnIndex);
        return StringUtils.isBlank(json) ? null : this.parse(json);
    }

    public T parse(String json) {
        try {
            return JSONObject.parseObject(json, (Class<T>) getRawType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String toJson(T t) {
        try {
            return JSONObject.toJSONString(t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
