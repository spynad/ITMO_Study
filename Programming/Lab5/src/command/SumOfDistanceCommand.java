package command;

import managers.IRouteManager;

public class SumOfDistanceCommand implements Command{
    IRouteManager routeManager;

    SumOfDistanceCommand(IRouteManager routeManager) {
        this.routeManager = routeManager;
    }

    public void execute() {
        routeManager.sumOfDistance();
    }
}
