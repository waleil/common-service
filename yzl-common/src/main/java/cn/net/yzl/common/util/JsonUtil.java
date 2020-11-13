package cn.net.yzl.common.util;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * json Util 工具类
 */
public class JsonUtil {


    public static void main(String[] args) {
        String json = "{\"success\":true,\"A\":{\"address\": \"address2\",\"name\":\"haha2\",\"id\":2,\"email\":\"email2\"},"
                + "\"B\":{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}}";
        try {
            Map<String, Object> stringObjectMap = readJson2Map(json);
            System.err.println(toJsonStr("stringObjectMap"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     * 将Map转换为对象
     *
     * @param paramMap
     * @param cls
     * @return
     */
    public static <T> T parseMap2Object(Map<String, Object> paramMap, Class<T> cls) {
        String json = JSONUtil.toJsonStr(paramMap);
        return JSONUtil.toBean(json, cls);
    }


    public static Map<String, Object> readJson2Map(String json) {
        return JSONUtil.toBean(json, Map.class);
    }

    public static String readMap2Json(Map<String, Object> map) throws Exception {
        return JSONUtil.toJsonStr(map);
    }

    /**
     * 获取在线解析xml文件
     *
     * @param url
     * @return
     */
    public static String loadJson(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL urlObject = new URL(url);
            URLConnection uc = urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    uc.getInputStream()));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    /**
     * 将对象转为json串
     *
     * @param obj
     * @return
     */
    public static String toJsonStr(Object obj) {

        return JSONUtil.toJsonStr(obj);
    }

    /**
     * 将json串转为对象
     *
     * @param jsonString
     * @param clazz
     * @return
     */
    public static <T> T getObjectFromJSONString(String jsonString, Class<T> clazz) {
        return JSONUtil.toBean(JSONUtil.parseObj(jsonString), clazz);
    }

    /**
     * json 转 List<T>
     */
    public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
        List<T> ts = (List<T>) new JSONArray(jsonString).toList(clazz);
        return ts;
    }
}
