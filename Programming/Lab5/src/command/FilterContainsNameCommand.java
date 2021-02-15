package command;

import managers.IRouteManager;

/**
 * Класс-команда, реализующая поиск и вывод элементов фыващзш
 */
public class FilterContainsNameCommand implements Command{
    IRouteManager routeManager;
    String name;

    FilterContainsNameCommand(IRouteManager routeManager, String name) {
        this.routeManager = routeManager;
        this.name = name;
    }

    public void execute() {
        routeManager.filterContainsName(name);
    }
}
