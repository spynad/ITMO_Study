package command;

import commands.Command;
import commands.RouteCommand;
import exception.CommandExecutionException;
import exception.CommandNotFoundException;
import locale.ServerBundle;
import log.Log;
import route.Route;

import java.util.*;

/**
 * Класс, выбирающий и вызывающий команду для исполнения
 * @author spynad
 */
public class CommandInvoker {

    private final Map<String, Command> commands = new HashMap<>();
    private final Map<String, ServerCommand> serverCommands = new HashMap<>();
    private final CommandHistory commandHistory;

    /*public CommandInvoker(RouteCollectionManager routeManager, RouteWriter routeWriter) {
        this.routeManager = routeManager;
        this.routeWriter = routeWriter;
    }*/

    public CommandInvoker(CommandHistory commandHistory) {
        this.commandHistory = commandHistory;
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

    /**
     * Метод, выполняющий команду
     * @param inputString - название команды
     */
    public void execute(String inputString, Route route) throws CommandNotFoundException, CommandExecutionException {
        if (inputString == null) {
            Log.getLogger().error(ServerBundle.getString("exception.eof"));
            throw new CommandNotFoundException(ServerBundle.getString("exception.eof"));
        }
        Command command;
        String[] split = inputString.trim().split("\\s+");
        String[] args = Arrays.copyOfRange(split, 1, split.length);

        if(commands.containsKey(split[0].toLowerCase(Locale.ROOT))) {
            command = commands.get(split[0].toLowerCase(Locale.ROOT));
            command.setArgs(args);
            commandHistory.pushHistory(inputString);

            if(route == null) {
                command.execute();
            } else {
                RouteCommand routeCommand = (RouteCommand) command;
                routeCommand.execute(route);
            }
        } else {
            if (split[0].equals("")) {
                throw new CommandNotFoundException(ServerBundle.getString("exception.command_not_found"));
            } else {
                throw new CommandNotFoundException(ServerBundle.getString("exception.command_not_found") + split[0]);
            }
        }
    }

    public void execute(String inputString) throws CommandNotFoundException, CommandExecutionException {
        if (inputString == null) {
            Log.getLogger().error(ServerBundle.getString("exception.eof"));
            throw new CommandNotFoundException(ServerBundle.getString("exception.eof"));
        }
        ServerCommand command;
        String[] split = inputString.trim().split("\\s+");

        if(serverCommands.containsKey(split[0].toLowerCase())) {
            command = serverCommands.get(split[0].toLowerCase());
            command.execute();
        } else {
            if (split[0].equals("")) {
                throw new CommandNotFoundException(ServerBundle.getString("exception.command_not_found"));
            } else {
                throw new CommandNotFoundException(ServerBundle.getString("exception.command_not_found") + ": " + split[0]);
            }
        }
    }

    public boolean checkRouteRequirement(String str) throws CommandNotFoundException {
        String[] split = str.trim().split("\\s+");
        if (commands.get(split[0].toLowerCase()) == null) {
            throw new CommandNotFoundException(ServerBundle.getString("exception.command_not_found") + ": " + split[0].toLowerCase());
        }
        return commands.get(split[0].toLowerCase()).isRouteRequired();
    }
}
