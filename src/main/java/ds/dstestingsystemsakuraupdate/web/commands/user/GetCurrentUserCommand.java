package ds.dstestingsystemsakuraupdate.web.commands.user;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ds.dstestingsystemsakuraupdate.data.model.User;
import ds.dstestingsystemsakuraupdate.web.commands.Command;
import ds.dstestingsystemsakuraupdate.web.commands.InvalidCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class GetCurrentUserCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("currentUser");
        if(user!=null) {
            System.out.println(user.getLogin());
            return new Gson().toJson(user, User.class);
        } else {
            JsonObject jo = new JsonObject();
            jo.addProperty("nosession", true);
            return new Gson().toJson(jo);
        }
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse rep, JsonObject obj) throws IOException, ServletException {
        HttpSession oldSession = req.getSession();
        oldSession.invalidate();
        return new InvalidCommand("400", "This command is not support POST method").execute(req, rep);
    }
}
