package com.fxft.cheyoufuwu.common.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by ChenDJ on 2015/7/24.<br>
 */
public class JsonUtil {

    private static final int READ_BUFFER = 1024;

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
     * 读取db.json数据库配置文本
     * <br>Created 2014年8月7日 下午5:52:14
     * @param in 文件数据流
     * @return 配置文件内容的json字符串
     * @author       chenDJ
     */
    public static String readJsonFile(InputStream in){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] data = new byte[READ_BUFFER];
        int count = -1;
        try {
            while((count = in.read(data,0,READ_BUFFER)) != -1) {
                output.write(data, 0, count);
            }
            in.close();
//			Log.e(getClass().getSimpleName(), "数据库结构内容："+ new String(output.toByteArray(), "UTF-8"));
            String result = new String(output.toByteArray(), "UTF-8");
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
