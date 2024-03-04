package ds.testingsystem.web.commands.test;

import com.google.gson.JsonObject;
import ds.testingsystem.web.commands.Command;
import ds.testingsystem.web.commands.InvalidCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoadDashboardTestCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Long testId = Long.parseLong(req.getParameter("testId"));
        HttpSession session = req.getSession();
        session.setAttribute("currentTestId", testId);
        session.setAttribute("setTestPageStatus", false);
        resp.sendRedirect(req.getContextPath()+"/test.jsp");
        return "/test.jsp";
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse rep, JsonObject obj) throws IOException, ServletException {
        return new InvalidCommand("400", "This command does not support POST method").execute(req, rep);
    }
}
