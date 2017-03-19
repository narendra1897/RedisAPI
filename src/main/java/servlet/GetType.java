package servlet;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Narendra on 2/1/2017.
 */
public class GetType {
    static final ArrayList<String> list_check = new ArrayList<String>(Arrays.asList("lpush","lrange","lindex","llen","lpop"));
    static final ArrayList<String> hash_check = new ArrayList<String>(Arrays.asList("hmset","hgetAll","hset","hvals","hmget","hdel"));
    static final ArrayList<String> set_check = new ArrayList<String>(Arrays.asList("sadd","sdiff","smembers","sinter","smove","srem","sunion"));

    public static String getType(String command){
        if(list_check.contains(command)){
            return "list";
        }

        if(hash_check.contains(command)){
            return "hash";
        }

        if(set_check.contains(command)){
            return "set";
        }
        return "not valid";
    }
}
