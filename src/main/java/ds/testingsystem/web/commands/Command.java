package ds.testingsystem.web.commands;


import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * <i>Abstract class</i>, blank to implementation of command
 */
public abstract class Command {
    /**
     * Execute command for GET method
     * @param req <i>HttpServletRequest</i> request data
     * @param resp <i>HttpServletResponse</i> response data
     * @return <i>String</i> JSON data - result of processing command
     * @throws IOException
     * @throws ServletException
     */
    public abstract String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException;

    /**
     * Execute command for POST method
     * @param req <i>HttpServletRequest</i> request data
     * @param rep <i>HttpServletResponse</i> response data
     * @param obj <i>JsonObject</i> JSON body of request
     * @return <i>String</i> JSON data - result of processing command
     * @throws IOException
     * @throws ServletException
     */
    public abstract String execute(HttpServletRequest req, HttpServletResponse rep, JsonObject obj) throws IOException, ServletException;

    public String toString(){
        return getClass().getSimpleName();
    }
}
