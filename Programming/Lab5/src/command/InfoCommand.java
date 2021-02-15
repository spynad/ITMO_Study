package command;

import managers.IRouteManager;

/**
 * Класс-команда, реализующая вывод информации о коллекции
 */
public class InfoCommand implements Command{
    IRouteManager routeManager;

    InfoCommand(IRouteManager routeManager) {
        this.routeManager = routeManager;
    }

    public void execute() {
        routeManager.info();
    }
}
