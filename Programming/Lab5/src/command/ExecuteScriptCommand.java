package command;

import managers.CollectionRouteManager;
import managers.IOManager;
import route.exceptions.InvalidArgumentException;

import java.io.*;

/**
 * Класс-команда, реализующая выполение "скрипта" из файла
 * @author spynad
 * @version govno
 */
public class ExecuteScriptCommand implements Command{
    CollectionRouteManager routeManager;
    IOManager fileManager;
    String[] args;
    String fileName;

    ExecuteScriptCommand(CollectionRouteManager routeManager, IOManager fileManager, String[] args) {
        this.routeManager = routeManager;
        this.fileManager = fileManager;
        this.args = args;
    }

    public void execute() {
        try {
            if (args.length == 1) {
                fileName = args[0];
            } else {
                throw new InvalidArgumentException("expected 1 argument, got " + args.length);
            }
        } catch (InvalidArgumentException iae) {
            System.err.println(iae.getMessage());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
                if (CommandInvoker.scripts.contains(fileName)) {
                    throw new IllegalStateException("recursive script call: " + fileName);
                }
                CommandInvoker.addScript(fileName);

                CommandInvoker commandInvoker = new CommandInvoker(routeManager, fileManager, reader);

                while (reader.ready()) {
                    String commands = reader.readLine();
                    commandInvoker.execute(commands);
                }
        } catch (IllegalStateException ise) {
            System.err.println("error while trying to execute the script");
        } catch (FileNotFoundException fnfe) {
            System.err.println("script file not found: " + fileName);
        } catch (IOException e) {
            System.err.println("error while reading the script: " + e.getMessage());
        }
        /*try {
            while (Application.getInput().ready()) {
                String commands = Application.getInput().readLine();
                commandInvoker.execute(commands);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        CommandInvoker.removeScript(fileName);
        //Application.setInput(new BufferedReader(new InputStreamReader(System.in)));
    }
}
