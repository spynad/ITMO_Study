package command;

import managers.IRouteManager;

/**
 * Класс-команда, реализующая удаление всех элементов коллекции
 * @author spynad
 * @version govno
 */
public class ClearCommand implements Command{
    IRouteManager routeManager;

    ClearCommand(IRouteManager routeManager) {
        this.routeManager = routeManager;
    }

    public void execute() {
        routeManager.clear();
    }
}
