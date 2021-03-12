package command;

import main.RouteCollectionManager;

/**
 * Класс-команда, реализующая вывод информации о коллекции
 */
public class InfoCommand implements Command{
    RouteCollectionManager routeManager;

    InfoCommand(RouteCollectionManager routeManager) {
        this.routeManager = routeManager;
    }

    public void execute() {
        routeManager.info();
    }
}
