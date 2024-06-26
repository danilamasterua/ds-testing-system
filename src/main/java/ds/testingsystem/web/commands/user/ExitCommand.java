package ds.testingsystem.web.commands.user;

import com.google.gson.JsonObject;
import ds.testingsystem.web.commands.Command;
import ds.testingsystem.web.commands.ErrorCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class ExitCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse rep, JsonObject obj) throws IOException, ServletException {
        return new ErrorCommand("500", "This command does not support an GET method").execute(req, rep);
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        session.invalidate();
        resp.sendRedirect(req.getContextPath()+"/index.jsp");
        return "invalidate";
    }
}
