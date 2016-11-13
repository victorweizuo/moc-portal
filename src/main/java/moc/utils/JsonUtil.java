package moc.utils;


import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

/**
 * Created by Administrator on 2016/9/17.
 */
public class JsonUtil {
    private static JsonSerializer serializer=new JsonSerializer();
    private static JsonParser parser=new JsonParser();

    public static String encode(Object object){
        return serializer.serialize(object);
    }

    public static <T> T decode(String json,Class<T> clazz){
        return parser.parse(json,clazz);
    }
}
