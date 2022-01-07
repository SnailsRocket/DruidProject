package com.xubo.druid.http;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author xubo
 * @Date 2022/1/7 9:35
 * 测试 RestTemplate 性能瓶颈
 */
public class RestTemplateTest {

    @Test
    public void mulRequest() {
        ExecutorService pool = Executors.newFixedThreadPool(50);
        pool.execute(new Runnable() {
            @Override
            public void run() {
                initialRest();
               /* String url = "http://localhost:10010/wms/testRestTemplate";
                HttpHeaders headers = new HttpHeaders();
                MediaType mediaType = MediaType.parseMediaType("application/josn;charset=UTF-8");
                headers.setContentType(mediaType);
                headers.add("Accept", MediaType.APPLICATION_JSON.toString());
                RestTemplate restTemplate = new RestTemplate();
                String s = JSON.toJSONString(Arrays.asList(1, 2, 3, 4, 5, 6));
                HttpEntity<String> entity = new HttpEntity<>(s, headers);
                String result = restTemplate.postForObject(url, entity, String.class);
                System.out.println(Thread.currentThread().getName() + "--" + result);*/
            }
        });

    }

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(30);
        pool.execute(new Runnable() {
            @Override
            public void run() {
               //initialRest();
                System.out.println(Thread.currentThread().getName() + "threadName");
            }
        });
    }


    public static void initialRest() {
        Integer i = Integer.valueOf(1);
        // synchronized (i) {
            String url = "http://localhost:10010/wms/testRestTemplate";
            try {
                HttpHeaders headers = new HttpHeaders();
                MediaType mediaType = MediaType.parseMediaType("application/josn;charset=UTF-8");
                headers.setContentType(mediaType);
                headers.add("Accept", MediaType.APPLICATION_JSON.toString());
                RestTemplate restTemplate = new RestTemplate();
                String s = JSON.toJSONString(Arrays.asList(1, 2, 3, 4, 5, 6));
                HttpEntity<String> entity = new HttpEntity<>(s, headers);
                String result = restTemplate.postForObject(url, entity, String.class);
                System.out.println(Thread.currentThread().getName() + "--" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        //}
    }

    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


}
