package command;

import main.RouteCollectionManager;
import exception.InvalidArgumentException;

/**
 * Класс-команда, реализующая поиск и вывод элементов фыващзш
 */
public class FilterContainsNameCommand implements Command{
    RouteCollectionManager routeManager;
    String[] args;

    FilterContainsNameCommand(RouteCollectionManager routeManager) {
        this.routeManager = routeManager;
    }

    @Override
    public void setArgs(String[] args) {
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
