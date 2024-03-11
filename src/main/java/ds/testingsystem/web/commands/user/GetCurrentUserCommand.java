package ds.testingsystem.web.commands.user;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ds.testingsystem.data.model.User;
import ds.testingsystem.web.commands.Command;
import ds.testingsystem.web.commands.ErrorCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


/**
 * Command what return JSON <i>currentUser:<b>User</b></i> from current session, or return JSON <code>nosession=false</code>, if session is not exist
 */
public class GetCurrentUserCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("currentUser");
        if(user!=null) {
            return user.toJson();
        } else {
            JsonObject jo = new JsonObject();
            jo.addProperty("nosession", true);
            return new Gson().toJson(jo);
        }
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse rep, JsonObject obj) {
        HttpSession oldSession = req.getSession();
        oldSession.invalidate();
        return new ErrorCommand("400", "This command is not support POST method").execute(req, rep);
    }
}
