package Redis;

import redis.clients.jedis.Jedis;

/**
 * Created by Shailendra on 1/26/2017.
 */
public class ConnectRedis {
    public static Jedis conn(){
        Jedis jedis = new Jedis("localhost");
        return jedis;
       // System.out.println("connected to the server");
        //System.out.println("the ping result is : " + jedis.ping());
    }
}
