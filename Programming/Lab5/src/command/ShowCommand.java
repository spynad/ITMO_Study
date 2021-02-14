package command;

import managers.IRouteManager;

public class ShowCommand implements Command{
    IRouteManager routeManager;

    ShowCommand(IRouteManager routeManager) {
        this.routeManager = routeManager;
    }

    public void execute() {
        routeManager.show();
    }
}
