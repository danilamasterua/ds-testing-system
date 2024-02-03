package ds.testingsystem.web.commands.test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ds.testingsystem.web.commands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ds.testingsystem.web.commands.InvalidCommand;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class GetTestPageStatusCommand extends Command {
    private final Logger logger = LogManager.getLogger(GetOwnedTestListCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        boolean testPageStatus = (boolean) session.getAttribute("setTestPageStatus");
        JsonObject jo = new JsonObject();
        jo.addProperty("testPageStatus", testPageStatus);
        return new Gson().toJson(jo);
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse rep, JsonObject obj) throws IOException, ServletException {
        logger.error("This command does not support POST method");
        return new InvalidCommand("500", "This command does not support POST method").execute(req, rep);
    }
}
