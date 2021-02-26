package command;

import managers.CollectionRouteManager;

/**
 * Класс-команда, реализующая вывод в стандартный поток суммы занчений поля distance Кщгеу
 */
public class SumOfDistanceCommand implements Command{
    CollectionRouteManager routeManager;

    SumOfDistanceCommand(CollectionRouteManager routeManager) {
        this.routeManager = routeManager;
    }

    public void execute() {
        routeManager.sumOfDistance();
    }
}
