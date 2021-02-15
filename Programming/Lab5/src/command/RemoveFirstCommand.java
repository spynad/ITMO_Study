package command;

import managers.IRouteManager;

/**
 * Класс-команда, реализующая удаление первого элемента коллекции
 */
public class RemoveFirstCommand implements Command{
    IRouteManager routeManager;

    RemoveFirstCommand(IRouteManager routeManager) {
        this.routeManager = routeManager;
    }
    public void execute() {
        routeManager.removeFirst();
    }
}
