package ds.testingsystem.web.commands.test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import ds.testingsystem.data.model.Test;
import ds.testingsystem.data.model.User;
import ds.testingsystem.data.repos.TestRepository;
import ds.testingsystem.web.commands.Command;
import ds.testingsystem.web.commands.InvalidCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class GetOwnedTestListCommand extends Command {
    private final Logger logger = LogManager.getLogger(GetOwnedTestListCommand.class);
    private final TestRepository repos = new TestRepository(Test.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            HttpSession session = req.getSession(false);
            User currentUser = (User) session.getAttribute("currentUser");
            List<Test> testList = repos.getTestListByOwner(currentUser);
            JsonArray jsonArray = new JsonArray();
            testList.forEach(test -> {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("id", test.getId());
                jsonObject.addProperty("name", test.getName());
                jsonArray.add(jsonObject);
            });
            return new Gson().toJson(jsonArray);
        } catch (Exception e){
            logger.error(e.getMessage());
            return new InvalidCommand("505", e.getMessage()).execute(req, resp);
        }
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse rep, JsonObject obj) throws IOException, ServletException {
        logger.error("This command does not support POST method");
        return new InvalidCommand("500", "This command does not support POST method").execute(req, rep);
    }
}
