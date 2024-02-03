package ds.testingsystem.web.commands;

import ds.testingsystem.web.commands.test.GetAvailableTestsCommand;
import ds.testingsystem.web.commands.test.GetOwnedTestListCommand;
import ds.testingsystem.web.commands.test.GetTestPageStatusCommand;
import ds.testingsystem.web.commands.test.LoadCreationTestPageCommand;
import ds.testingsystem.web.commands.user.ExitCommand;
import ds.testingsystem.web.commands.user.GetCurrentUserCommand;
import ds.testingsystem.web.commands.user.LoginCommand;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

/**
 * Container of commands
 */
public class CommandContainer {
    private static final HashMap<String, Command> commands = new HashMap<>();
    private static final Logger logger = LogManager.getLogger(CommandContainer.class);

    static {
        //Main command
        commands.put("login", new LoginCommand());
        commands.put("getCurrentUser", new GetCurrentUserCommand());
        commands.put("exit", new ExitCommand());
        commands.put("getAvailableTestList", new GetAvailableTestsCommand());
        commands.put("getOwnedTestList", new GetOwnedTestListCommand());
        commands.put("creationTestPage", new LoadCreationTestPageCommand());
        commands.put("getTestPageStatus", new GetTestPageStatusCommand());

        logger.info("Command container successfully initialized");
        logger.info("Count commands -> "+commands.size());
        System.out.println("Command container successfully initialized\nCount commands -> "+commands.size());
    }

    /**
     * Get object of type <i>Command</i>
     * @param command <i>String</i> name of command
     * @return <i>Command</i> command
     */
    public static Command get(String command){
        if(!commands.containsKey(command)||command==null){
            logger.info("command \""+command+"\" is not found");
            return new InvalidCommand();
        }
        logger.info("execute command \""+command+"\"");
        return commands.get(command);
    }
}
