package ds.dstestingsystemsakuraupdate.web.commands;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class InvalidCommand extends Command {
    private String errorCode;
    private String stackTrace;

    public InvalidCommand(){
        this.errorCode="404";
        this.stackTrace="Command not found";
    }

    public InvalidCommand(String errorCode, String stackTrace){
        this.errorCode=errorCode;
        this.stackTrace=stackTrace;
    }
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        JsonObject jo = new JsonObject();
        jo.addProperty("error", true);
        jo.addProperty("errorCode", errorCode);
        jo.addProperty("stackTrace", stackTrace);
        return new Gson().toJson(jo);
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse rep, JsonObject obj) throws IOException, ServletException {
        return new InvalidCommand("500", "This method is not support POST method").execute(req, rep);
    }
}
