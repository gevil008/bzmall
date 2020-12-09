package com.baizhi;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestTemplateTests extends AppRunTests {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void test1(){
        // 被请求的地址
        String url = "http://localhost:8802/menu/getMenuList";

        /**
         * get 开头的都是get请求
         *
         * getForObject
         * 参数1 请求地址
         * 参数2 响应参数的类型（类对象）String
         * 参数3 请求参数（可不写
         * 返回值就是被请求接口响应的数据
         */

        // String result = restTemplate.getForObject(url, String.class);
        // System.err.println(result);

        /**
         * getForEntity
         * 参数1 请求地址
         * 参数2 响应参数的类型（类对象）String
         * 参数3 请求参数（可不写
         *
         * 返回值是响应体对象
         * 在响应体对象中 不仅有响应的数据 还有响应码（200 400 500）等
         *
         * 参数拼接：可以直接在rul上拼接参数
         */
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        HttpStatus statusCode = responseEntity.getStatusCode();
        System.err.println(statusCode);
        String body = responseEntity.getBody();
        System.err.println(body);
    }
}
