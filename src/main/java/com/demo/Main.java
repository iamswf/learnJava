package com.demo;

import com.alibaba.fastjson.JSONObject;

/**
 * @author sunwenfei
 * @date 2021-03-03 14:55
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("ddd");

        JSONObject obj = JSONObject.parseObject("{\"name\": 123}");
        obj.put("abc", "{}");
        obj.put("def", new JSONObject());
        System.out.println(obj);
    }
}
