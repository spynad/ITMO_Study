package commands;

import exception.CommandExecutionException;
import route.Route;

public interface RouteCommand extends Command {
    void execute(Route route) throws CommandExecutionException;
}
