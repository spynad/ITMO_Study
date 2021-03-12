package command;

abstract public class AbstractCommand implements Command{
    private final boolean routeRequired;

    AbstractCommand (boolean b) {
        routeRequired = b;
    }

    public boolean isRouteRequired() {
        return routeRequired;
    }

    public abstract void execute();
}
