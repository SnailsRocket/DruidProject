package com.xubo.druid.entity.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
    public String sid;

    /**
     * 
     */
    public String sname;

    /**
     * 
     */
    public String sbirth;

    /**
     * 
     */
    public String ssex;

    @TableField(exist = false)
    public static final long serialVersionUID = 1L;


    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("images", "[{\"id\": null, \"url\": \"http://192.168.1.54:18082/file/858d0ff5-4eb6-422b-9793-2fc7565e03e5.jpg\", \"type\": \"1\", \"orderNum\": null}, {\"id\": null, \"url\": \"http://192.168.1.54:18082/file/0f6f95d2-8e5c-4a33-926b-7f3b3741ce92.jpg\", \"type\": \"1\", \"orderNum\": null}, {\"id\": null, \"url\": \"http://192.168.1.54:18082/file/021eadfd-6d72-4e87-806b-010d214ee3cf.jpg\", \"type\": \"1\", \"orderNum\": null}, {\"id\": null, \"url\": \"http://192.168.1.54:18082/file/3bca4fbe-efe3-41db-8738-4265fb6a9137.jpg\", \"type\": \"1\", \"orderNum\": null}, {\"id\": null, \"url\": \"http://192.168.1.54:18082/file/54dc44c0-d44f-487e-96a3-f100dd56da3b.jpg\", \"type\": \"1\", \"orderNum\": null}, {\"id\": null, \"url\": \"http://192.168.1.54:18082/file/23f48f68-73e5-4579-92c6-a520be4364bb.jpg\", \"type\": \"1\", \"orderNum\": null}, {\"id\": null, \"url\": \"http://192.168.1.54:18082/file/264914f7-f86b-43c3-af1a-8a134a911160.jpg\", \"type\": \"2\", \"orderNum\": null}]");
        JSONArray images = jsonObject.getJSONArray("images");
        JSONObject jsonObject1 = JSON.parseObject(String.valueOf(images.get(1)), JSONObject.class);
        String url = jsonObject1.getString("url");
        System.out.println(url);
        System.out.println(images.size());
        System.out.println(images.get(1));

        String str = "https://csepay.jinzhoubank.com/epaygate/pay.htm?service=pay_service&sign=pkh6pvqaw0l1ryrvnikan38iecxk82c1&partner=1234567890&out_trade_no=5325432525432&subject=subject&show_url=http://www.baidu.com&body=testBody&total_fee=10&fee_type=1&spbill_create_ip=127.0.0.1&trans_channel=pc-";

    }

}