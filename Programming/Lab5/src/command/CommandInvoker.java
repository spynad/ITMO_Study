package command;

import managers.IRouteManager;

import java.util.Arrays;
import java.util.HashMap;

public class CommandInvoker {
    IRouteManager routeManager;

    public CommandInvoker(IRouteManager routeManager) {
        this.routeManager = routeManager;
    }


    public void execute(String commandName) {
        Command command;
        String[] str1 = commandName.split("\\s+");
        System.out.println(Arrays.toString(str1));
        if (str1.length == 0) {
            throw new IllegalStateException("try again.");
        }
        switch (str1[0]) {
            case "help":
                command = new HelpCommand();
                break;
            case "exit":
                command = new ExitCommand();
                break;
            case "info":
                command = new InfoCommand(routeManager);
                break;
            default:
                throw new IllegalStateException("no command registered for " + commandName);
        }
        command.execute(str1);
        /*if (command == null) {
            throw new IllegalStateException("no command registered for " + commandName);
        }*/
    }
}
