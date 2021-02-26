package command;

import managers.CollectionRouteManager;
import route.exceptions.InvalidArgumentException;

/**
 * Класс-команда, реализующая поиск и вывод элементов фыващзш
 */
public class FilterContainsNameCommand implements Command{
    CollectionRouteManager routeManager;
    String[] args;

    FilterContainsNameCommand(CollectionRouteManager routeManager, String[] args) {
        this.routeManager = routeManager;
        this.args = args;
    }

    public void execute() {
        try {
            if (args.length == 1) {
                routeManager.filterContainsName(args[0]);
            } else {
                throw new InvalidArgumentException("expected 1 argument, got" + args.length);
            }
        } catch (InvalidArgumentException iae) {
            System.err.println(iae.getMessage());
        }

    }
}
