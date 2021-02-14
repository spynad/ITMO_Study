package command;

import managers.IRouteManager;

public class FilterContainsNameCommand implements Command{
    IRouteManager routeManager;
    String name;

    FilterContainsNameCommand(IRouteManager routeManager, String name) {
        this.routeManager = routeManager;
        this.name = name;
    }

    public void execute() {
        routeManager.filterContainsName(name);
    }
}
