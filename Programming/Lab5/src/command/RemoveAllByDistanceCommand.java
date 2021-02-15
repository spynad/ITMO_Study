package command;

import managers.IRouteManager;

/**
 * Класс-команда, реализующая удаление всех элементов коллекции, у которых поле distance=заданному
 */
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
