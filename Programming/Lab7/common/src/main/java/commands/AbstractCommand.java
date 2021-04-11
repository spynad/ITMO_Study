package commands;

import exception.CommandExecutionException;

abstract public class AbstractCommand implements Command {
    private final boolean routeRequired;

    public AbstractCommand (boolean b) {
        routeRequired = b;
    }

    public AbstractCommand () {routeRequired = false;}

    public boolean isRouteRequired() {
        return routeRequired;
    }

    public abstract void execute() throws CommandExecutionException;
}
