package com.wx.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Fun Description //TODO
 * @Date 2020/5/28 16:56 28
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
public class JsonUtils {
    public static String toJson(Object obj) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        return gson.toJson(obj);
    }
}
