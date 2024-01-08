package ds.dstestingsystemsakuraupdate.web.commands.user;

import com.google.gson.*;
import ds.dstestingsystemsakuraupdate.data.model.User;
import ds.dstestingsystemsakuraupdate.data.service.UserService;
import ds.dstestingsystemsakuraupdate.web.commands.Command;
import ds.dstestingsystemsakuraupdate.web.commands.InvalidCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.*;

import java.io.IOException;

public class LoginCommand extends Command {
    Logger logger = LogManager.getLogger(LoginCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession oldSession = req.getSession();
        oldSession.invalidate();
        return new InvalidCommand("400", "This command is not support GET method").execute(req, resp);
    }
    public String execute(HttpServletRequest req, HttpServletResponse resp, JsonObject obj) throws IOException, ServletException{
        HttpSession oldSession = req.getSession();
        oldSession.invalidate();
        HttpSession session = req.getSession();
        logger.info("Execute command -> "+this);
        try {
            String login = obj.get("login").getAsString();
            String password = obj.get("pass").getAsString();
            User user = UserService.getUser(login);
            if (user != null && user.checkPassword(password)) {
                session.setAttribute("currentUser", user);
                return "true";
            } else {
                return new InvalidCommand("401", "Password or login/email incorrect").execute(req, resp);
            }
        } catch (Exception e){
            logger.error(e.getMessage());
            return new InvalidCommand("500", e.getMessage()).execute(req, resp);
        }
    }
}
