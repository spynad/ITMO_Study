package command;

import collection.RouteCollectionManager;
import commands.Command;
import commands.RouteCommand;
import exception.CommandExecutionException;
import exception.CommandNotFoundException;
import file.RouteReader;
import file.RouteWriter;
import log.Log;
import response.Creator;
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

    Map<String, ServerCommand> serverCommands = new HashMap<>();

    static Set<String> scripts = new HashSet<>();

    RouteCollectionManager routeManager;

    Creator creator;

    RouteWriter routeWriter;

    public CommandInvoker(RouteCollectionManager routeManager, RouteWriter routeWriter) {
        this.routeManager = routeManager;
        this.routeWriter = routeWriter;
    }

    public CommandInvoker(RouteCollectionManager routeManager, RouteWriter writer, Creator creator) {
        this.routeManager = routeManager;
        this.creator = creator;
        this.routeWriter = writer;
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

    public void addCommand(String commandName, Command command) {
        commands.put(commandName, command);
    }

    public void addServerCommand(String commandName, ServerCommand command) {
        serverCommands.put(commandName, command);
    }

    //TODO: добавлять поле необходимости Route ради двух классов - херовая идея

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

    public List<String> getHistory() {
        return history;
    }


    /**
     * Метод, выполняющий команду
     * @param inputString - название команды
     */
    public void execute(String inputString, Route route) throws CommandNotFoundException, CommandExecutionException {
        if (inputString == null) {
            Log.getLogger().error("Input string is empty");
            throw new CommandNotFoundException("Input string is empty");
        }
        Command command;
        String[] split = inputString.trim().split("\\s+");
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

    public void execute(String inputString) throws CommandNotFoundException, CommandExecutionException {
        if (inputString == null) {
            Log.getLogger().error("Input string is empty");
            throw new CommandNotFoundException("Input string is empty");
        }
        ServerCommand command;
        String[] split = inputString.trim().split("\\s+");
        String[] args = Arrays.copyOfRange(split, 1, split.length);

        if(serverCommands.containsKey(split[0].toLowerCase(Locale.ROOT).trim())) {
            command = serverCommands.get(split[0].toLowerCase(Locale.ROOT).trim());
            command.execute();
        } else {
            if (split[0].equals("")) {
                throw new CommandNotFoundException("unknown command");
            } else {
                throw new CommandNotFoundException("unknown command: " + split[0]);
            }
        }
    }

    public boolean checkRouteRequirement(String str) throws CommandNotFoundException {
        String[] split = str.trim().split("\\s+");
        if (commands.get(split[0].toLowerCase(Locale.ROOT).trim()) == null) {
            throw new CommandNotFoundException("unknown command: " + split[0].toLowerCase(Locale.ROOT).trim());
        }
        return commands.get(split[0].toLowerCase(Locale.ROOT).trim()).isRouteRequired();
    }
}
