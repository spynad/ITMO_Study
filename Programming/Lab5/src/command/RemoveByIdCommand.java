package command;

import managers.IRouteManager;

/**
 * Класс-команда, реализующая удаление элемента коллекции по его ID
 */
public class RemoveByIdCommand implements Command{
    IRouteManager routeManager;
    final int id;

    RemoveByIdCommand(IRouteManager routeManager, int id) {
        this.routeManager = routeManager;
        this.id = id;
    }
    public void execute() {
        routeManager.removeById(id);
    }
}
