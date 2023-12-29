package ds.dstestingsystemsakuraupdate.web.commands;

import ds.dstestingsystemsakuraupdate.web.commands.user.GetCurrentUserCommand;
import ds.dstestingsystemsakuraupdate.web.commands.user.LoginCommand;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class CommandContainer {
    private static final HashMap<String, Command> commands = new HashMap<>();
    private static final Logger logger = LogManager.getLogger(CommandContainer.class);

    static {
        //Main command
        commands.put("login", new LoginCommand());
        commands.put("getCurrentUser", new GetCurrentUserCommand());

        logger.info("Command container successfully initialized");
        logger.info("Count commands -> "+commands.size());
    }

    public static Command get(String command){
        if(!commands.containsKey(command)||command==null){
            logger.info("command \""+command+"\" is not found");
            return new InvalidCommand();
        }
        logger.info("execute command \""+command+"\"");
        return commands.get(command);
    }
}
