package servlet;

import redis.clients.jedis.Jedis;
import servlet.ConnectRedis;

import java.util.Map;

/**
 * Created by narendra on 1/31/2017.
 */
public class CommandExecute {
    Jedis jedis;
    public CommandExecute(String[] server_address){
        jedis = ConnectRedis.connection(server_address);
    }
    public Long lpush(Map<String,String[]> inmap){
        Long back = 0L;
        for(Map.Entry<String,String[]> entry : inmap.entrySet()){
            if(entry.getKey().equals("redis")) continue;
            if(entry.getKey().equals("command")) continue;
            if(entry.getKey().equals("param1")) continue;
            else{
                back = jedis.lpush((inmap.get("param1"))[0],(entry.getValue())[0]);
            }
        }
        return back;
    }
}
