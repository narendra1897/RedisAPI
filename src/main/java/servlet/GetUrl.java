package servlet;

import org.json.simple.JSONArray;

import org.json.simple.JSONObject;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GetUrl extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        response.setContentType("text.html");
        PrintWriter out = response.getWriter();

        Map<String,String[]> inmap = request.getParameterMap();
        for(Map.Entry<String,String[]> entry : inmap.entrySet()){
            System.out.println("key is " + entry.getKey());
            System.out.println("value is " + (entry.getValue())[0]);
        }

        String urlIn = inmap.get("command")[0];
        String[] parameters = urlIn.split(" ");
        System.out.println("the command string is " + parameters);

        String commandType = GetType.getType(parameters[0]);

        if(commandType.equals("list")){
            Jedis jedis = ConnectRedis.connection(inmap.get("redis"));
            RedisList redislist = new  RedisList();
            //LPUSH
            if(parameters[0].equals("lpush")) {
                Long l = redislist.lpush(parameters, jedis);
                out.println("<h1>" + l + "</h1>");
            }
            //LRANGE
            else if(parameters[0].equals("lrange")){
                response.setContentType("application/json");
                PrintWriter jsonout = response.getWriter();
                List<String> output = redislist.lrange(parameters,jedis);
               // Iterator it = output.iterator();
                JSONArray jsonarray = new JSONArray();
                jsonarray.addAll(output);
                out.print(jsonarray);

                //the above one is to be tested for Json
                //below code work fine for normal output case
                /*List<String> output = redislist.lrange(parameters,jedis);
                Iterator it = output.iterator();
                while(it.hasNext()) {
                    out.print("<h1>" + it.next() + "</h1>");
                }*/
            }
            //LINDEX
            else if(parameters[0].equals("lindex")){
                //the above is to test the json
                //the below work fine for normal situtaion
                String s = redislist.lindex(parameters,jedis);
                out.print("<h1>" + s + "</h1>");
            }
            //LLEN
            else if(parameters[0].equals("llen")){
                Long l = redislist.llen(parameters,jedis);
                out.print("<h1>" + l +"</h1>");
            }
            //LPOP
            else if(parameters[0].equals("lpop")){
                String s = redislist.lpop(parameters,jedis);
                out.print("<h1>" + s +"</h1>");
            }
            else{

            }
        }

        //HASH IS IMPLEMENTED
        if(commandType.equals("hash")){
            Jedis jedis = ConnectRedis.connection(inmap.get("redis"));
            RedisHash redishash = new RedisHash();

            if(parameters[0].equals("hmset")){
                String s = redishash.hmset(parameters,jedis);
                out.print("<h1>" + s +"</h1>");
            }

            if(parameters[0].equals("hdel")){
                Long l = redishash.hdel(parameters,jedis);
                out.print("<h1>" + l +"</h1>");
            }

            if(parameters[0].equals("hmget")){
                List<String> output = redishash.hmget(parameters,jedis);
                Iterator it = output.iterator();
                while(it.hasNext()) {
                    out.print("<h1>" + it.next() + "</h1>");
                }
            }

            if(parameters[0].equals("hvals")){
                List<String> output = redishash.hvals(parameters,jedis);
                Iterator it = output.iterator();
                while(it.hasNext()) {
                    out.print("<h1>" + it.next() + "</h1>");
                }
            }

            if(parameters[0].equals("hset")){
                Long l = redishash.hset(parameters,jedis);
                out.print("<h1>" + l +"</h1>");
            }

            if(parameters[0].equals("hgetAll")){
                response.setContentType("application/json");
                PrintWriter jsonout = response.getWriter();

                Map<String,String> map = redishash.hgetall(parameters,jedis);
                JSONObject jsonobject = new JSONObject();
                jsonobject.putAll(map);
                jsonout.print(jsonobject);
                //the above code is to test the Json for Object map
                //the below work fine for normal case and to be deleted once the above work fine
                /*for(Map.Entry<String,String> entry : map.entrySet()){
                    out.print("<h1>" + entry.getKey() + "   "+entry.getValue()+"</h1>");
                }*/
            }

        }

        //SET IMPLEMENTATION
        if(commandType.equals("set")){
            RedisSet redisset = new RedisSet();
            Jedis jedis = ConnectRedis.connection(inmap.get("redis"));

            if(parameters[0].equals("sadd")){
                Long l = redisset.sadd(parameters,jedis);
                out.print("<h1>" + l +"</h1>");
            }

            if(parameters[0].equals("sdiff")){
                Set<String> output = redisset.sdiff(parameters,jedis);
                Iterator it = output.iterator();
                while(it.hasNext()) {
                    out.print("<h1>" + it.next() + "</h1>");
                }
            }

            if(parameters[0].equals("sinter")){
                Set<String> output = redisset.sinter(parameters,jedis);
                Iterator it = output.iterator();
                while(it.hasNext()) {
                    out.print("<h1>" + it.next() + "</h1>");
                }
            }

            if(parameters[0].equals("smove")){
                Long l = redisset.smove(parameters,jedis);
                out.print("<h1>" + l +"</h1>");
            }

            if(parameters[0].equals("srem")){
                Long l = redisset.srem(parameters,jedis);
                out.print("<h1>" + l +"</h1>");
            }

            if(parameters[0].equals("sunion")){
                Set<String> output = redisset.sunion(parameters,jedis);
                Iterator it = output.iterator();
                while(it.hasNext()) {
                    out.print("<h1>" + it.next() + "</h1>");
                }
            }

            if(parameters[0].equals("smembers")){
                Set<String> output = redisset.smembers(parameters,jedis);
                Iterator it = output.iterator();
                while(it.hasNext()) {
                    out.print("<h1>" + it.next() + "</h1>");
                }
            }
        }


    }


}
