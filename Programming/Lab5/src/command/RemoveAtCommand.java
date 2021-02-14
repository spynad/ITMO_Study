package command;

import managers.IRouteManager;

public class RemoveAtCommand implements Command{
    IRouteManager routeManager;
    int index;

    RemoveAtCommand(IRouteManager routeManager, int index) {
        this.routeManager = routeManager;
        this.index = index;
    }

    public void execute() {
        routeManager.removeAt(index);
    }
}
