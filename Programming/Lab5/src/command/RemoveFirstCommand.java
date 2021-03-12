package command;

import main.RouteCollectionManager;

/**
 * Класс-команда, реализующая удаление первого элемента коллекции
 */
public class RemoveFirstCommand implements Command{
    RouteCollectionManager routeManager;

    RemoveFirstCommand(RouteCollectionManager routeManager) {
        this.routeManager = routeManager;
    }
    public void execute() {
        routeManager.removeFirst();
    }
}
