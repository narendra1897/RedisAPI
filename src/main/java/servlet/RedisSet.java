package servlet;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Created by Shailendra on 2/3/2017.
 */
public class RedisSet {

    public Long sadd(String[] input,Jedis jedis){
        String[] arr = new String[input.length -2];
        for(int i=2; i<input.length; i++){
            arr[i-2] = input[i];
        }
        return jedis.sadd(input[1],arr);
    }

    public Set<String> sdiff(String[] input,Jedis jedis){
        String[] arr = new String[input.length -1];
        for(int i=1; i<input.length; i++){
            arr[i-1] = input[i];
        }
        return jedis.sdiff(arr);
    }

    public Set<String> sinter(String[] input,Jedis jedis){
        String[] arr = new String[input.length -1];
        for(int i=1; i<input.length; i++){
            arr[i-1] = input[i];
        }
        return jedis.sinter(arr);
    }

    public Long smove(String[] input,Jedis jedis){
        return jedis.smove(input[1],input[2],input[3]);
    }

    public Long srem(String[] input,Jedis jedis){
        String[] arr = new String[input.length -2];
        for(int i=2; i<input.length; i++){
            arr[i-2] = input[i];
        }
        return jedis.srem(input[1],arr);
    }

    public Set<String> sunion(String[] input,Jedis jedis){
        String[] arr = new String[input.length -1];
        for(int i=1; i<input.length; i++){
            arr[i-1] = input[i];
        }
        return jedis.sunion(arr);
    }

    public Set<String> smembers(String[] input,Jedis jedis){
        return jedis.smembers(input[1]);
    }
}
