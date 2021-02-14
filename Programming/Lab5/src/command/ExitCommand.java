package command;

import managers.CommandManager;

public class ExitCommand implements Command{
    CommandManager m = new CommandManager();

    public void execute(String[] args) {
        System.exit(0);
    }
}
