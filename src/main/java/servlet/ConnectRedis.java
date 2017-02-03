package servlet;

import redis.clients.jedis.Jedis;

/**
 * Created by Shailendra on 1/31/2017.
 */
public class ConnectRedis {
    static Jedis jedis;

    public static Jedis connection(String[] server_address){
        if(server_address[0].equals("localhost")){
            jedis =  new Jedis("localhost");
            return jedis;
        }
        else{
            for(int i=0; i < server_address.length; i++){
                String[] address = server_address[i].split(":");
                jedis = new Jedis(address[0],Integer.parseInt(address[1]));
            }
            return jedis;
        }
    }
}
