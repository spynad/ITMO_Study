package commands;

import client.Application;
import exception.CommandExecutionException;
import exception.CommandNotFoundException;
import route.Route;

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

    BufferedReader reader;

    main.RouteWriter routeWriter;


    public Map<String, Command> getCommands() {
        return commands;
    }

    public void setCommands(Map<String, Command> commands) {
        this.commands = commands;
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
    public void execute(String inputString, Route route) throws CommandNotFoundException, CommandExecutionException {
        if (inputString == null) {
            System.err.println("eof detected, terminating the program");
            Application.setIsRunning(false);
            return;
        }
        Command command;
        String[] split = inputString.split("\\s+");
        String[] args = Arrays.copyOfRange(split, 1, split.length);

        if(commands.containsKey(split[0].toLowerCase(Locale.ROOT).trim())) {
            command = commands.get(split[0].toLowerCase(Locale.ROOT).trim());
            command.setArgs(args);
            pushHistory(inputString);

            if(route == null) {
                command.execute();
            } else {
                RouteCommand routeCommand = (RouteCommand) command;
                routeCommand.execute(route);
            }
        } else {
            if (split[0].equals("")) {
                throw new CommandNotFoundException("unknown command");
            } else {
                throw new CommandNotFoundException("unknown command: " + split[0]);
            }
        }
    }
}
