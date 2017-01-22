package servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Shailendra on 1/22/2017.
 */
public class RedisServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html");

        System.out.println("hello");
        Map parameter = request.getParameterMap();
        System.out.println(parameter);
    }
}
