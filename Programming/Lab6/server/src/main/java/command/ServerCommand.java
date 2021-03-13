package command;

import commands.Command;
import exception.CommandExecutionException;

public interface ServerCommand {
    void execute() throws CommandExecutionException;
}
