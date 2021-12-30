package com.xubo.druid.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author xubo
 * @Date 2021/12/29 11:25
 */
@Component
public class RedisUtils {

    private RedisTemplate redisTemplate;

    private ValueOperations valueOperations;

    @Autowired
    public RedisUtils(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        valueOperations=redisTemplate.opsForValue();
    }

    /**  默认过期时长，单位：秒 */
    private final static long DEFAULT_EXPIRE = 60 * 60 * 24;
    /**  不设置过期时长 */
    private final static long NOT_EXPIRE = -1;

    public boolean getLock(String lockId, long millisecond) {
        Boolean success = redisTemplate.opsForValue().setIfAbsent(lockId, "lock",
                millisecond, TimeUnit.MILLISECONDS);
        return success != null && success;
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void set(String key, Object value, long expire){
        valueOperations.set(key, toJson(value));
        if(expire != NOT_EXPIRE){
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }


    public void setHours(String key, Object value, long expire){
        valueOperations.set(key, toJson(value));
        if(expire != NOT_EXPIRE){
            redisTemplate.expire(key, expire, TimeUnit.HOURS);
        }
    }

    public void set(String key, Object value){
        set(key, value, DEFAULT_EXPIRE);
    }

    public <T> T get(String key, Class<T> clazz, long expire) {
        String value = (String) valueOperations.get(key);
        if(expire != NOT_EXPIRE){
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value == null ? null : fromJson(value, clazz);
    }


    public <T> T get(String key, Class<T> clazz) {
        return get(key, clazz, NOT_EXPIRE);
    }

    public String get(String key, long expire) {
        String value = (String) valueOperations.get(key);
        if(expire != NOT_EXPIRE){
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value;
    }

    public String get(String key) {
        return get(key, NOT_EXPIRE);
    }

    public void setList(String key, List list){
        valueOperations.set(key, JSONArray.toJSONString(list));
        redisTemplate.expire(key, DEFAULT_EXPIRE, TimeUnit.SECONDS);
    }

    public <T> List<T> getList(String key){
        List<T> getList = new ArrayList<>();
        String val = (String) valueOperations.get(key);
        JSONArray array = JSONArray.parseArray(val);
        if(array != null && array.size() > 0){
            for (Object object : array){
                getList.add((T) object);
            }
        }
        return getList;
    }

    /**
     * Object转成JSON数据
     */
    private String toJson(Object object){
        if(object instanceof Integer || object instanceof Long || object instanceof Float ||
                object instanceof Double || object instanceof Boolean || object instanceof String){
            return String.valueOf(object);
        }
        return JSON.toJSONString(object);
    }

    /**
     * JSON数据，转成Object
     */
    private <T> T fromJson(String json, Class<T> clazz){
        return JSON.parseObject(json,clazz);
    }

    public Long increment(String key, long i) {
        return redisTemplate.opsForValue().increment(key,i);
    }


}
