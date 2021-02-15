package command;

import managers.ClientManager;
import managers.IFileManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Класс-команда, реализующая выполение "скрипта" из файла
 * @author spynad
 * @version govno
 */
public class ExecuteScriptCommand implements Command{
    CommandInvoker commandInvoker;
    IFileManager fileManager;
    String filePath;
    ArrayList<String> commands;

    ExecuteScriptCommand(CommandInvoker commandInvoker, IFileManager fileManager, String filePath) {
        this.commandInvoker = commandInvoker;
        this.fileManager = fileManager;
        this.filePath = filePath;
    }

    public void execute() {
        try {
            ClientManager.setInput(new BufferedReader(new FileReader(filePath)));
        } catch (IOException ioe) {
            System.err.println("Exception while trying to read the file");
        }
        commandInvoker.addScript(filePath);
        try {
            while (ClientManager.getInput().ready()) {
                String commands = ClientManager.getInput().readLine();
                commandInvoker.getCommand(commands);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        commandInvoker.removeScript(filePath);
        ClientManager.setInput(new BufferedReader(new InputStreamReader(System.in)));
    }
}
