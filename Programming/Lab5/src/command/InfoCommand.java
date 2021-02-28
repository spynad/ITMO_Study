package command;

import main.CollectionRouteManager;

/**
 * Класс-команда, реализующая вывод информации о коллекции
 */
public class InfoCommand implements Command{
    CollectionRouteManager routeManager;

    InfoCommand(CollectionRouteManager routeManager) {
        this.routeManager = routeManager;
    }

    public void execute() {
        routeManager.info();
    }
}
