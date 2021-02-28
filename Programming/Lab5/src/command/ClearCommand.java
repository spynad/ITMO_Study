package command;

import main.CollectionRouteManager;

/**
 * Класс-команда, реализующая удаление всех элементов коллекции
 * @author spynad
 * @version govno
 */
public class ClearCommand implements Command{
    CollectionRouteManager routeManager;

    ClearCommand(CollectionRouteManager routeManager) {
        this.routeManager = routeManager;
    }

    public void execute() {
        routeManager.clear();
    }
}
