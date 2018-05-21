package com.tinnkm.application.util.json;

import com.google.gson.*;

public class JsonUtils {
    private static Gson json ;
    static {
        // 默认使用驼峰转下划线的命名策略
        json = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }
    public static JsonObject string2json(String source){
        JsonElement parse = new JsonParser().parse(source);
        return parse.getAsJsonObject();
    }

    public static <T> T json2object(String source,Class<T>  t){
       return json.fromJson(source, t);
    }

    /**
     * 简单一级的json串获取
     * @param source
     * @param filedName
     * @return
     */
    public static String getFieldValue(String source,String filedName){
        return string2json(source).get(filedName).getAsString();
    }

    public static String object2json(Object o){
        return json.toJson(o);
    }

}
