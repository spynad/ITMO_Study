package command;

import managers.IRouteManager;

public class RemoveFirstCommand implements Command{
    IRouteManager routeManager;

    RemoveFirstCommand(IRouteManager routeManager) {
        this.routeManager = routeManager;
    }
    public void execute() {
        routeManager.removeFirst();
    }
}
