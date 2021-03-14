package commands;

import client.Application;

public class ExitCommand extends AbstractCommand{
    @Override
    public void execute() {
        Application.setIsRunning(false);
    }
}
