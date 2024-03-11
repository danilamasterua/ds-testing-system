package ds.testingsystem.web.commands.test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ds.testingsystem.data.model.Test;
import ds.testingsystem.data.model.User;
import ds.testingsystem.web.commands.Command;
import ds.testingsystem.web.commands.ErrorCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetAvailableTestsCommand extends Command {
    private final Logger logger = LogManager.getLogger(GetAvailableTestsCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        if(currentUser!=null){
            List<Test> accessedTests = new ArrayList<>();
            currentUser.getGroups().forEach(edGroup -> {
                edGroup.getAccessedTests().forEach(accessedTest ->{
                    accessedTests.add(accessedTest.getTest());
                });
            });
            return new Gson().toJson(accessedTests);
        } else {
            logger.error("Unauthorized access");
            session.invalidate();
            return new ErrorCommand("401", "Attempt to unauthorized access").execute(req, resp);
        }
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse rep, JsonObject obj) throws IOException, ServletException {
        logger.error("This command does not support POST method");
        return new ErrorCommand("500", "This command does not support POST method").execute(req, rep);
    }
}
