package command;

import managers.IFileManager;
import managers.IRouteManager;

import java.util.*;

/**
 * Класс, выбирающий и вызывающий команду для исполнения
 * @author spynad
 * @version govno
 */
//TODO: CommandInvoker не должен заниматься базовым парсингом аргументов, это может делать, например, ClientManager (вроде)
public class CommandInvoker {
    /**
     * Стек с историей команд
     */
    Stack<String> history = new Stack<>();

    /**
     * Множество с названиями файлов скриптов
     */
    Set<String> scripts = new HashSet<>();
    IRouteManager routeManager;
    IFileManager fileManager;

    public CommandInvoker(IRouteManager routeManager, IFileManager fileManager) {
        this.routeManager = routeManager;
        this.fileManager = fileManager;
    }

    /**
     * Метод, который добавляет название файла скрипта в множество
     * @param name - название файла
     */
    public void addScript(String name) {
        scripts.add(name);
    }

    /**
     * Метод, который убирает название файла скрипта из множества
     * @param name - название файла
     */
    public void removeScript(String name) {
        scripts.remove(name);
    }

    /**
     * Метод, который кладет в стек последнюю выполненную команду
     * @param command - название команды
     */
    public void pushHistory(String command) {
        if(history.size() > 11) {
            history.remove(history.size() - 1);
        }
        history.push(command.toLowerCase(Locale.ROOT));
    }

    /**
     * Метод, вызывающий команду
     * @param command - класс-команда
     */
    public void execute(Command command) {
        command.execute();
    }

    /**
     * Метод, возвращающий команду
     * @param commandName - название команды
     * @return - класс-команда
     */
    public Command getCommand(String commandName) {
        Command command;
        String str2 = commandName.trim();
        String[] str1 = str2.split("\\s+");

        switch (str1[0].toLowerCase(Locale.ROOT).trim()) {
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
                command = new AddCommand(routeManager, str1);
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
                if (str1.length == 2)  {
                    command = new UpdateCommand(routeManager, str1);
                } else {
                    throw new IllegalStateException("invalid arguments");
                }
                break;
            default:
                throw new IllegalStateException("no command registered for " + commandName.trim());
        }
        pushHistory(commandName);
        return command;
    }
}
