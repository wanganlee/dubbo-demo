package com.demo.tool;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

/**
 * @author developer.wang
 * @date 2020/3/25
 */
public class DemoIdea {

    /**
     * this is a demo for type json string.
     */
    public static void main(String[] args) {
        String json = "{\"name\":\"developer_wang\",\"age\":30}";
        Gson gson = new GsonBuilder().create();
        Map<String, Integer> map = gson.fromJson(json, new TypeToken<Map<String, Integer>>() {
        }.getType());
        System.out.println(map);
    }


    public void test() {
        String num;
        // num.no
    }


}
