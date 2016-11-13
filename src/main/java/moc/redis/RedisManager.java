package moc.redis;

import redis.embedded.RedisServer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/17.
 */
public class RedisManager {
    private static RedisServer server = null;

    private static Map<String, String> data = new HashMap<>();

    public synchronized static RedisServer getServer() {
        if (server == null) {
            try {
                server = new RedisServer(9091);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return server;
    }

    public static String getData(String key){
        return data.get(key);
    }

    public static String setData(String key,String value){
        return data.put(key,value);
    }


}
