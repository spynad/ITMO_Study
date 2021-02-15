package command;

import managers.IRouteManager;

/**
 * Класс-команда, реализующая вывод в стандартный поток всей коллекциииии
 */
public class ShowCommand implements Command{
    IRouteManager routeManager;

    ShowCommand(IRouteManager routeManager) {
        this.routeManager = routeManager;
    }

    public void execute() {
        routeManager.show();
    }
}
