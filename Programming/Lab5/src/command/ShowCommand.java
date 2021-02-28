package command;

import main.CollectionRouteManager;

/**
 * Класс-команда, реализующая вывод в стандартный поток всей коллекциииии
 */
public class ShowCommand implements Command{
    CollectionRouteManager routeManager;

    ShowCommand(CollectionRouteManager routeManager) {
        this.routeManager = routeManager;
    }

    public void execute() {
        routeManager.show();
    }
}
