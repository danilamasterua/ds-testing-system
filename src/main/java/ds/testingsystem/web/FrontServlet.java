package ds.testingsystem.web;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ds.testingsystem.web.commands.Command;
import ds.testingsystem.web.commands.CommandContainer;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Main servlet for front controller<br>
 * for work with it, you need to declare parameter <b>command</b> in your request body
 * @Required
 * <i>command</i> - name of command
 */
@WebServlet("/do")
public class FrontServlet extends HttpServlet {
    private final Logger logger = LogManager.getLogger(FrontServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        logger.info("Start JS front servlet");
        String command = req.getParameter("command");
        logger.info("With command ->"+command);
        Command c = CommandContainer.get(command);
        logger.info("Command "+c.toString()+" has been received");
        String json = c.execute(req, resp);
        forward(json, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Start JS front servlet");
        req.setCharacterEncoding("UTF-8");
        String s;
        logger.info("Start POST method");
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()))){
            s = reader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
        JsonObject jo = new Gson().fromJson(s, JsonObject.class);
        String command = jo.get("command").getAsString();
        System.out.println(command);
        logger.info("With command ->"+command);
        Command c = CommandContainer.get(command);
        logger.info("Command "+c.toString()+" has been received");
        String json = c.execute(req, resp, jo);
        forward(json, resp);
    }

    private void forward(String json, HttpServletResponse response) throws IOException {
        if(json!=null){
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().append(json);
        }
    }
}
