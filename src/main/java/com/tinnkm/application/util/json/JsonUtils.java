package com.tinnkm.application.util.json;

import com.google.gson.*;

/**
 * @author tinnkm
 */
public class JsonUtils {
    private JsonUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static Gson json;

    static {
        // 默认使用驼峰转下划线的命名策略
        json = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }

    /**
     * 字符串转json
     *
     * @param source 源字符串
     * @return json对象
     */
    public static JsonObject string2json(String source) {
        JsonElement parse = new JsonParser().parse(source);
        return parse.getAsJsonObject();
    }

    /**
     * json转实体
     *
     * @param source 源字符串
     * @param t      实体类型
     * @param <T>    实体
     * @return 返回的实体
     */
    public static <T> T json2object(String source, Class<T> t) {
        return json.fromJson(source, t);
    }

    /**
     * 简单一级的json串获取
     *
     * @param source    源字符串
     * @param filedName 字段名
     * @return 字段值
     */
    public static String getFieldValue(String source, String filedName) {
        return string2json(source).get(filedName).getAsString();
    }


    /**
     * 对象转json
     *
     * @param o 对象实体
     * @return json字符串
     */
    public static String object2json(Object o) {
        return json.toJson(o);
    }

}
