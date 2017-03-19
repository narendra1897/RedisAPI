package servlet;

import redis.clients.jedis.Jedis;

/**
 * Created by Narendra on 1/31/2017.
 */
public class ConnectRedis {
    static Jedis jedis;

    public static Jedis connection(String[] hostPortValue){
        String[] details = hostPortValue[0].split(" ");
        jedis = new Jedis(details[0],Integer.parseInt(details[1]));
        return jedis;
    }
}
