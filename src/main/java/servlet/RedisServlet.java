package servlet;

import Redis.ConnectRedis;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import  java.util.List;

/**
 * Created by Shailendra on 1/22/2017.
 */
public class RedisServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html");
        String country="country";
        System.out.println("hello");
        //Map parameter = request.getParameterMap();
        //System.out.println(parameter);
        Jedis jedis = ConnectRedis.conn();
        /*System.out.println("the server ping " + jedis.ping());
        jedis.set("name","narendra");
        jedis.set(country,"idnia");
        System.out.println("the name is " + jedis.get("name"));
        System.out.println("your country is : " + jedis.get(country));
        jedis.lpush("students","shailendra");
        jedis.lpush("students","john");
        List<String> mylist = jedis.lrange("students",0,-1);
        for(String elements : mylist){
            System.out.print(elements + "  ");
        }*/
        //System.out.println("the query is " + request.getQueryString());
        System.out.println("see below");
        String input = request.getQueryString();
        String[] out = input.split("-");
        for(String elem : out){
            System.out.println(elem + "  ");
        }
    }
}
