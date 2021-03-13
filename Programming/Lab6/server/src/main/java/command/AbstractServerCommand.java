package command;

import exception.CommandExecutionException;

public abstract class AbstractServerCommand implements ServerCommand{
    public abstract void execute() throws CommandExecutionException;
}
