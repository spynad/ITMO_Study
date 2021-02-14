package command;

import managers.IRouteManager;

public class InfoCommand implements Command{
    IRouteManager routeManager;

    InfoCommand(IRouteManager routeManager) {
        this.routeManager = routeManager;
    }

    public void execute() {
        routeManager.info();
    }
}
