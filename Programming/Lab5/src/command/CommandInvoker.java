package command;

import main.Application;
import main.CsvFileRouteWriter;
import main.RouteWriter;
import main.CollectionRouteManager;
import main.ScriptRouteReader;

import java.io.BufferedReader;
import java.util.*;

/**
 * Класс, выбирающий и вызывающий команду для исполнения
 * @author spynad
 * @version govno
 */
//TODO: CommandInvoker не должен заниматься базовым парсингом аргументов, это может делать, например, ClientManager (вроде)
public class CommandInvoker {

    Stack<String> history = new Stack<>();

    static Set<String> scripts = new HashSet<>();

    CollectionRouteManager routeManager;

    RouteWriter fileManager;

    BufferedReader reader;

    public CommandInvoker(CollectionRouteManager routeManager) {
        this.routeManager = routeManager;
    }

    public CommandInvoker(CollectionRouteManager routeManager, BufferedReader reader) {
        this.routeManager = routeManager;
        this.reader = reader;
    }

    /**
     * Метод, который добавляет название файла скрипта в множество
     * @param name - название файла
     */
    public static void addScript(String name) {
        scripts.add(name);
    }

    /**
     * Метод, который убирает название файла скрипта из множества
     * @param name - название файла
     */
    public static void removeScript(String name) {
        scripts.remove(name);
    }

    public Set<String> getScripts() {
        return scripts;
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
     * Метод, выполняющий команду
     * @param inputString - название команды
     */
    public void execute(String inputString) {
        if (inputString == null) {
            System.err.println("eof detected, terminating the program");
            Application.setIsRunning(false);
            return;
        }
        Command command;
        String[] split = inputString.split("\\s+");
        String[] args = Arrays.copyOfRange(split, 1, split.length);

        switch (split[0].toLowerCase(Locale.ROOT).trim()) {
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
                command = new SaveCommand(new CsvFileRouteWriter(routeManager, Application.getFileName()));
                break;
            case "remove_all_by_distance":
                command = new RemoveAllByDistanceCommand(routeManager, args);
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
                command = new RemoveByIdCommand(routeManager, args);
                break;
            case "remove_at":
                command = new RemoveAtCommand(routeManager, args);
                break;
            case "filter_contains_name":
                command = new FilterContainsNameCommand(routeManager, args);
                break;
            case "history":
                command = new HistoryCommand(history);
                break;
            case "add":
                command = new AddCommand(routeManager, reader);
                break;
            case "execute_script":
                command = new ExecuteScriptCommand(routeManager, new ScriptRouteReader(reader, routeManager), args);
                break;
            case "update":
                command = new UpdateCommand(routeManager, args, reader);
                break;
            default:
                if (split[0].equals("")) {
                    throw new IllegalStateException("unknown command");
                } else {
                    throw new IllegalStateException("unknown command: " + split[0]);
                }
        }
        pushHistory(inputString);
        command.execute();
    }
}
