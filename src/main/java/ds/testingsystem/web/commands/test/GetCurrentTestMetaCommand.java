package ds.testingsystem.web.commands.test;

import com.google.gson.JsonObject;
import ds.testingsystem.data.model.Test;
import ds.testingsystem.data.repos.TestRepository;
import ds.testingsystem.web.commands.Command;
import ds.testingsystem.web.commands.ErrorCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class GetCurrentTestMetaCommand extends Command {
    TestRepository repository = new TestRepository(Test.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        Long testId = (Long) session.getAttribute("currentTestId");
        System.out.println("testId:"+testId);
        if(testId!=null) {
            Test test = repository.getById(testId);
            return test.toJson();
        } else {
            return "false";
        }
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse rep, JsonObject obj) throws IOException, ServletException {
        return new ErrorCommand("400", "This command does not support POST method").execute(req, rep);
    }
}
