package servlet;

import Redis.ConnectRedis;

import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

/**
 * Created by Narendra on 1/30/2017.
 */
public class Set extends HttpServlet {
    public void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException{
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        String s = request .getQueryString();
        String[] input = s.split("&");
        Jedis jedis = ConnectRedis.conn();

        if(input[0].equals("lpush")){
            int count_value = input.length - 1;
            for(int i=2 ; i<=count_value ; i++){
                jedis.lpush(input[1] , input[i]);
            }
        }

        if(input[0].equals("lrange")){
            List<String> l = jedis.lrange(input[1],Long.parseLong(input[2]),Long.parseLong(input[3]));
            for(String values : l){
               out.println(values);
            }
        }

        if(input[0].equals("lpop")){
            String pop_value = jedis.lpop(input[1]);
            if(pop_value == null){
                out.println("<h1>" + "no item in the list" + "</h1>");
            }
            else {
                out.println("<h1>" + pop_value + "</h1>");
            }
        }

        if(input[0].equals("lrem")){
            out.print("<h1>" + jedis.lrem(input[1],Long.parseLong(input[2]),input[3]) + "</h1>");
        }

        if(input[0].equals("lindex")){
            out.print("<h1>" + jedis.lindex(input[1],Long.parseLong(input[2])) + "</h1");
        }

        if(input[0].equals("linsert")){
            if(input[2].equals("before")){
                out.print("<h1>" + jedis.linsert(input[1], BinaryClient.LIST_POSITION.BEFORE ,input[3],input[4]) + "</h1>");
            }
            if(input[2].equals("after")){
                out.print("<h1>" + jedis.linsert(input[1], BinaryClient.LIST_POSITION.AFTER ,input[3],input[4]) + "</h1>");
            }
        }

        if(input[0].equals("llen")){
            out.print("<h1>" + jedis.llen(input[1]) + "</h1>");
        }

        if(input[0].equals("lset")){
            out.print("<h1>" + jedis.lset(input[1],Long.parseLong(input[2]),input[3]) + "</h1>");
        }

        if(input[0].equals("ltrim")){
            out.print("<h1>" + jedis.ltrim(input[1], Long.parseLong(input[2]),Long.parseLong(input[3])) + "</h1>");
        }



    }
}
