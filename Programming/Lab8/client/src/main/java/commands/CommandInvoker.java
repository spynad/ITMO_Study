package commands;

import exception.CommandExecutionException;
import exception.CommandNotFoundException;
import locale.ClientLocale;
import route.Route;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс, выбирающий и вызывающий команду для исполнения
 * @author spynad
 */
public class CommandInvoker {
    private final Map<String, Command> commands = new HashMap<>();
    private static final Set<String> scripts = new HashSet<>();
    private final Pattern argPattern = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");

    public Set<String> getScripts() {
        return scripts;
    }

    public void addCommand(String commandName, Command command) {
        commands.put(commandName, command);
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
     * Метод, выполняющий команду
     * @param inputString - название команды
     */
    public void execute(String inputString, Route route) throws CommandNotFoundException, CommandExecutionException {
        if (inputString == null) {
            return;
        }
        Command command;
        List<String> matchList = new ArrayList<>();
        Matcher regexMatcher = argPattern.matcher(inputString.trim());
        while (regexMatcher.find()) {
            if (regexMatcher.group(1) != null) {
                matchList.add(regexMatcher.group(1));
            } else if (regexMatcher.group(2) != null) {
                matchList.add(regexMatcher.group(2));
            } else {
                matchList.add(regexMatcher.group());
            }
        }
        String[] args = matchList.toArray(new String[0]);
        //String[] split = inputString.split("\\s+");
        //String[] args = Arrays.copyOfRange(split, 1, split.length);

        if(commands.containsKey(args[0].toLowerCase().trim())) {
            command = commands.get(args[0].toLowerCase().trim());
            command.setArgs(Arrays.copyOfRange(args, 1, args.length));

            if(route == null) {
                command.execute();
            } else {
                RouteCommand routeCommand = (RouteCommand) command;
                routeCommand.execute(route);
            }
        } else {
            if (args[0].equals("")) {
                throw new CommandNotFoundException(ClientLocale.getString("exception.command_not_found"));
            } else {
                throw new CommandNotFoundException(ClientLocale.getString("exception.command_not_found") + ": " + args[0]);
            }
        }
    }
}
