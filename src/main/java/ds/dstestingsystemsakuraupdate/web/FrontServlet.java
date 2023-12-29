package ds.dstestingsystemsakuraupdate.web;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ds.dstestingsystemsakuraupdate.web.commands.Command;
import ds.dstestingsystemsakuraupdate.web.commands.CommandContainer;
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
        forward(json, req, resp);
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
        forward(json, req, resp);
    }

    private void forward(String json, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(json!=null){
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().append(json);
        }
    }
}
