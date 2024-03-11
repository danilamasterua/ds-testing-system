package ds.testingsystem.web.commands;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;


/**
 * <b><i>Command</i></b> for any type of errors
 */
@AllArgsConstructor
public class ErrorCommand extends Command {
    private String errorCode;
    private String stackTrace;

    public ErrorCommand(){
        this.errorCode="404";
        this.stackTrace="Command not found";
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        JsonObject jo = new JsonObject();
        jo.addProperty("error", true);
        jo.addProperty("errorCode", errorCode);
        jo.addProperty("stackTrace", stackTrace);
        return new Gson().toJson(jo);
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse rep, JsonObject obj) {
        return new ErrorCommand("500", "This method is not support POST method").execute(req, rep);
    }
}
