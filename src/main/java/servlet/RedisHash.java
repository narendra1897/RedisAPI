package servlet;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Narendra on 2/3/2017.
 */
public class RedisHash {

    public String hmset(String[] input,Jedis jedis){
        System.out.println("hmset is called");
        Map<String,String> map = new HashMap<String,String>();
        for(int i=1; i<=((input.length)/2) - 1 ; i++){
            map.put(input[i*2],input[i*2+1]);
        }
        return jedis.hmset(input[1],map);
    }

    public Long hdel(String[] input,Jedis jedis){
        String[] arr = new String[input.length - 2];
        System.out.println("the function hdel is called");
        for(int i=2;i<input.length;i++){
            arr[i-2] = input[i];
        }
        System.out.println("the array arr is");
        for(String s : arr){
            System.out.println(s + " ");
        }
        return jedis.hdel(input[1],arr);
    }

    public List<String> hmget(String[] input,Jedis jedis){
        String[] arr = new String[input.length - 2];
        for(int i=2;i<input.length;i++){
            arr[i-2] = input[i];
        }
        return jedis.hmget(input[1],arr);
    }

    public List<String> hvals(String[] input,Jedis jedis){
        return jedis.hvals(input[1]);
    }

    public Long hset(String[] input,Jedis jedis){
        return jedis.hset(input[1],input[2],input[3]);
    }

    public Map<String,String> hgetall(String[] input,Jedis jedis){
        return jedis.hgetAll(input[1]);
    }
}
