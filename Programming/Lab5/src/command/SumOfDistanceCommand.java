package command;

import managers.IRouteManager;

/**
 * Класс-команда, реализующая вывод в стандартный поток суммы занчений поля distance Кщгеу
 */
public class SumOfDistanceCommand implements Command{
    IRouteManager routeManager;

    SumOfDistanceCommand(IRouteManager routeManager) {
        this.routeManager = routeManager;
    }

    public void execute() {
        routeManager.sumOfDistance();
    }
}
