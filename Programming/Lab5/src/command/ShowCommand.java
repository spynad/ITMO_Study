package command;

import main.RouteCollectionManager;

/**
 * Класс-команда, реализующая вывод в стандартный поток всей коллекциииии
 */
public class ShowCommand implements Command{
    RouteCollectionManager routeManager;

    ShowCommand(RouteCollectionManager routeManager) {
        this.routeManager = routeManager;
    }

    public void execute() {
        routeManager.show();
    }
}
