package servlet;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;

/**
 * Created by Shailendra on 2/1/2017.
 */
public class RedisList {

    public Long lpush(String[] input,Jedis jedis){
        String[] arr = new String[input.length - 2];
        for(int i=2; i<input.length; i++){
            arr[i-2] = input[i];
        }
        return jedis.lpush(input[1],arr);
        //the below lpush logic to be delted once the above one works fine
        /*Long out = 0L;
        if(input[0].equals("lpush")){
           for(int i=2; i<input.length; i++){
                out = jedis.lpush(input[1],input[i]);
            }
        }
        return out;*/
    }

    public List<String> lrange(String[] input, Jedis jedis){
        return jedis.lrange(input[1],Long.parseLong(input[2]),Long.parseLong(input[3]));
    }

    public String lindex(String[] input, Jedis jedis){
        return jedis.lindex(input[2],Long.parseLong(input[3]));
    }

    public Long llen(String input[],Jedis jedis){
        return jedis.llen(input[1]);
    }

    public String lpop(String[] input,Jedis jedis){
        return jedis.lpop(input[1]);
    }
}

