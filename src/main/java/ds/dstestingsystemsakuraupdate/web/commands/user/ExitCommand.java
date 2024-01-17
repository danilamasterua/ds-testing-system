package ds.dstestingsystemsakuraupdate.web.commands.user;

import com.google.gson.JsonObject;
import ds.dstestingsystemsakuraupdate.web.commands.Command;
import ds.dstestingsystemsakuraupdate.web.commands.InvalidCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class ExitCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse rep, JsonObject obj) throws IOException, ServletException {
        return new InvalidCommand("500", "This command does not support an GET method").execute(req, rep);
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        session.invalidate();
        return "invalidate";
    }
}
