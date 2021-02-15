package command;

import managers.IFileManager;
import managers.IRouteManager;

import java.util.*;

public class CommandInvoker {
    Stack<String> history = new Stack<>();
    Set<String> scripts = new HashSet<>();
    IRouteManager routeManager;
    IFileManager fileManager;

    public CommandInvoker(IRouteManager routeManager, IFileManager fileManager) {
        this.routeManager = routeManager;
        this.fileManager = fileManager;
    }

    public void addScript(String name) {
        scripts.add(name);
    }

    public void removeScript(String name) {
        scripts.remove(name);
    }

    public void execute(String commandName) {
        Command command;
        String str2 = commandName.stripLeading();
        String[] str1 = str2.split("\\s+");

        switch (str1[0].toLowerCase(Locale.ROOT)) {
            case "help":
                command = new HelpCommand();
                break;
            case "exit":
                command = new ExitCommand();
                break;
            case "info":
                command = new InfoCommand(routeManager);
                break;
            case "clear":
                command = new ClearCommand(routeManager);
                break;
            case "save":
                command = new SaveCommand(fileManager);
                break;
            case "remove_all_by_distance":
                if(str1.length == 2) {
                    double distance = Double.parseDouble(str1[1]);
                    command = new RemoveAllByDistanceCommand(routeManager, distance);
                } else {
                    throw new IllegalStateException("invalid arguments. try again");
                }
                break;
            case "sum_of_distance":
                command = new SumOfDistanceCommand(routeManager);
                break;
            case "show":
                command = new ShowCommand(routeManager);
                break;
            case "remove_first":
                command = new RemoveFirstCommand(routeManager);
                break;
            case "remove_by_id":
                if(str1.length == 2) {
                    int id = Integer.parseInt(str1[1]);
                    command = new RemoveByIdCommand(routeManager, id);
                } else {
                    throw new IllegalStateException("invalid arguments. try again");
                }
                break;
            case "remove_at":
                if(str1.length == 2) {
                    int index = Integer.parseInt(str1[1]);
                    command = new RemoveAtCommand(routeManager, index);
                } else {
                    throw new IllegalStateException("invalid arguments. try again");
                }
                break;
            case "filter_contains_name":
                if(str1.length == 2) {
                    command = new FilterContainsNameCommand(routeManager, str1[1]);
                } else {
                    throw new IllegalStateException("invalid arguments. try again");
                }
                break;
            case "history":
                command = new HistoryCommand(history);
                break;
            case "add":
                if ((str1.length > 2) && (str1.length < 12)) {
                    command = new AddCommand(routeManager, str1);
                } else {
                    throw new IllegalStateException("invalid arguments. try again");
                }
                break;
            case "execute_script":
                if(str1.length == 2) {
                    if (scripts.contains(str1[1])) {
                        throw new IllegalStateException("recursive script call " + str1[1]);
                    }
                    command = new ExecuteScriptCommand(this, fileManager, str1[1]);
                } else {
                    throw new IllegalStateException("invalid arguments. try again");
                }
                break;
            case "update":
                if ((str1.length > 3) && (str1.length < 13)) {
                    command = new UpdateCommand(routeManager, str1);
                } else {
                    throw new IllegalStateException("invalid arguments");
                }
                break;
            default:
                throw new IllegalStateException("no command registered for " + commandName);
        }
        command.execute();
        if(history.size() > 11) {
            history.remove(history.size() - 1);
        }
        history.push(str1[0].toLowerCase(Locale.ROOT));
    }

}
