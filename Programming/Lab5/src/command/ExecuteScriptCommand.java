package command;

import managers.IFileManager;

import java.util.ArrayList;

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
        commands = fileManager.readFile(filePath);
        commandInvoker.addScriptName(filePath);
        for (String command : commands) {
            commandInvoker.execute(command);
        }
    }
}
