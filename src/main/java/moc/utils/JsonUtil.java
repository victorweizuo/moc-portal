package moc.utils;


import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by Administrator on 2016/9/17.
 */
public class JsonUtil {
    private static Gson gson=new Gson();

    public static String encode(Object object){

        return gson.toJson(object);
    }

    public static <T> T decode(String json,Class<T> clazz){

        return gson.fromJson(json,clazz);
    }

    public static <T> T decode(String json,Type type){
        return gson.fromJson(json,type);
    }
}
