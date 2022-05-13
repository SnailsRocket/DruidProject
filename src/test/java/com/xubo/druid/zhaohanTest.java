package com.xubo.druid;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @Author xubo
 * @Date 2022/5/12 16:10
 */
public class zhaohanTest {

    public static void main(String[] args) {
        String str = "{\"hasNext\":\"false\",\"message\":\"操作成功\",\"resultType\":\"1000\"}";
        String s = JSON.toJSONString(str);
        System.out.println("s = " + s);
        String s1 = JSON.parse(str).toString();
        System.out.println("s1 = " + s1);
        JSONObject jsonObject = JSON.parseObject(str, JSONObject.class);
        System.out.println("jsonObject = " + jsonObject);
        if(jsonObject.getString("hasNext").equals("false")) {
            System.out.println("success");
        }

//        JSONArray objects1 = JSONObject.parseArray(str);
//        System.out.println("objects1 = " + objects1);
//        JSONArray objects = JSONArray.parseArray(JSON.parse(str).toString());
//        System.out.println("objects = " + objects);
    }

}
