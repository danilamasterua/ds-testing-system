package ds.dstestingsystemsakuraupdate.web.commands;


import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public abstract class Command {
    public abstract String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException;
    public abstract String execute(HttpServletRequest req, HttpServletResponse rep, JsonObject obj) throws IOException, ServletException;

    public String toString(){
        return getClass().getSimpleName();
    }
}
