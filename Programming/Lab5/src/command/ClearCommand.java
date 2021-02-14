package command;


import managers.IRouteManager;

public class ClearCommand implements Command{
    IRouteManager routeManager;

    ClearCommand(IRouteManager routeManager) {
        this.routeManager = routeManager;
    }

    public void execute() {
        routeManager.clear();
    }
}
