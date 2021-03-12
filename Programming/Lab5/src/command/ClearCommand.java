package command;

import main.RouteCollectionManager;

/**
 * Класс-команда, реализующая удаление всех элементов коллекции
 * @author spynad
 * @version govno
 */
public class ClearCommand implements Command{
    RouteCollectionManager routeManager;

    ClearCommand(RouteCollectionManager routeManager) {
        this.routeManager = routeManager;
    }

    public void execute() {
        routeManager.clear();
    }
}
