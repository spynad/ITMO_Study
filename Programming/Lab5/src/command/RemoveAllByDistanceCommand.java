package command;

import managers.IRouteManager;

public class RemoveAllByDistanceCommand implements Command{
    IRouteManager routeManager;
    private final double distance;

    RemoveAllByDistanceCommand(IRouteManager routeManager, double distance) {
        this.routeManager = routeManager;
        this.distance = distance;
    }

    public void execute() {
        routeManager.removeAllByDistance(distance);
    }
}
