package command;

import main.RouteCollectionManager;

/**
 * Класс-команда, реализующая вывод в стандартный поток суммы занчений поля distance Кщгеу
 */
public class SumOfDistanceCommand implements Command{
    RouteCollectionManager routeManager;

    SumOfDistanceCommand(RouteCollectionManager routeManager) {
        this.routeManager = routeManager;
    }

    public void execute() {
        routeManager.sumOfDistance();
    }
}
