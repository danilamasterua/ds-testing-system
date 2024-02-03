package ds.testingsystem.web.commands.test;

import com.google.gson.JsonObject;
import ds.testingsystem.web.commands.Command;
import ds.testingsystem.web.commands.InvalidCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class LoadCreationTestPageCommand extends Command {
    Logger logger = LogManager.getLogger(LoadCreationTestPageCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        session.setAttribute("setTestPageStatus", true);
        resp.sendRedirect(req.getContextPath()+"/test.jsp");
        return "/test.jsp";
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse rep, JsonObject obj) throws IOException, ServletException {
        logger.error("This command does not support POST method");
        return new InvalidCommand("500", "This command does not support POST method").execute(req, rep);
    }
}
