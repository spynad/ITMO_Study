package command;

import managers.CollectionRouteManager;

/**
 * Класс-команда, реализующая удаление первого элемента коллекции
 */
public class RemoveFirstCommand implements Command{
    CollectionRouteManager routeManager;

    RemoveFirstCommand(CollectionRouteManager routeManager) {
        this.routeManager = routeManager;
    }
    public void execute() {
        routeManager.removeFirst();
    }
}
