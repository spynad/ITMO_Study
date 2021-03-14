package command;

import io.UserIO;
import main.*;

import java.io.BufferedReader;
import java.util.*;

/**
 * Класс, выбирающий и вызывающий команду для исполнения
 * @author spynad
 */
public class CommandInvoker {

    Stack<String> history = new Stack<>();

    Map<String, Command> commands = new HashMap<>();

    static Set<String> scripts = new HashSet<>();

    RouteCollectionManager routeManager;

    BufferedReader reader;

    UserIO userIO;

    RouteWriter routeWriter;

    public CommandInvoker(RouteCollectionManager routeManager, UserIO userIO, RouteWriter routeWriter) {
        this.routeManager = routeManager;
        this.userIO = userIO;
        this.routeWriter = routeWriter;
        putCommands();
    }

    public CommandInvoker(RouteCollectionManager routeManager, BufferedReader reader) {
        this.routeManager = routeManager;
        this.reader = reader;
        putCommands();
    }

    public void putCommands() {
        commands.put("help", new HelpCommand());
        commands.put("exit", new ExitCommand());
        commands.put("info", new InfoCommand(routeManager));
        commands.put("clear", new ClearCommand(routeManager));
        commands.put("save", new SaveCommand(routeWriter));
        commands.put("remove_all_by_distance", new RemoveAllByDistanceCommand(routeManager));
        commands.put("sum_of_distance", new SumOfDistanceCommand(routeManager));
        commands.put("show", new ShowCommand(routeManager));
        commands.put("remove_first", new RemoveFirstCommand(routeManager));
        commands.put("remove_by_id", new RemoveByIdCommand(routeManager));
        commands.put("remove_at", new RemoveAtCommand(routeManager));
        commands.put("filter_contains_name", new FilterContainsNameCommand(routeManager));
        commands.put("history", new HistoryCommand(history));
        commands.put("add", new AddCommand(routeManager, reader, userIO));
        commands.put("execute_script", new ExecuteScriptCommand(routeManager));
        commands.put("update", new UpdateCommand(routeManager, reader, userIO));
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
        String[] split = inputString.trim().split("\\s+");
        String[] args = Arrays.copyOfRange(split, 1, split.length);

        if(commands.containsKey(split[0].toLowerCase(Locale.ROOT).trim())) {
            command = commands.get(split[0].toLowerCase(Locale.ROOT).trim());
            command.setArgs(args);
            pushHistory(inputString);
            command.execute();
        } else {
            if (split[0].equals("")) {
                throw new IllegalStateException("unknown command");
            } else {
                throw new IllegalStateException("unknown command: " + split[0]);
            }
        }
    }
}
