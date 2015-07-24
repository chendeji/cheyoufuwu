package com.fxft.cheyoufuwu.common.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by ChenDJ on 2015/7/24.<br>
 */
public class JsonUtil {

    /**
     * 读取文件中的json字符串
     * @param file 文件
     * @return json字符串
     * @throws IOException 读取异常
     */
    public static String getJsonStringFromFile(File file) throws IOException {
        String json = null;
        if (file.exists() && !file.isDirectory()) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "";
            StringBuilder builder = new StringBuilder();
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
            json = builder.toString();
        }
        return json;
    }

    /**
     * 解析数组
     *
     * @param jsonString json字符串
     * @param clazz      解析类型
     * @param <T>        泛型类型
     * @return 列表
     */
    public static <T> List<T> parserJsonArray(String jsonString, Class<T> clazz) {
        List<T> list = null;
        if (TextUtils.isEmpty(jsonString))
            return null;
        Gson gson = new Gson();
        list = gson.fromJson(jsonString, new TypeToken<List<T>>() {
        }.getType());
        return list;
    }

    /**
     * 解析对象
     *
     * @param jsonString json字符串
     * @param clazz      解析类型
     * @param <T>
     * @return
     */
    public static <T> T parserJsonObject(String jsonString, Class<T> clazz) {
        T object = null;
        if (TextUtils.isEmpty(jsonString))
            return null;
        Gson gson = new Gson();
        object = gson.fromJson(jsonString, clazz);
        return object;
    }
}
