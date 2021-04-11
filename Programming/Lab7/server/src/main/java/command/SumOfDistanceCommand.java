package command;

import collection.RouteCollectionManager;
import commands.AbstractCommand;
import commands.Command;

/**
 * Класс-команда, реализующая вывод в стандартный поток суммы занчений поля distance Кщгеу
 */
public class SumOfDistanceCommand extends AbstractCommand implements Command {
    private final RouteCollectionManager routeManager;

    public SumOfDistanceCommand(RouteCollectionManager routeManager, boolean req) {
        super(req);
        this.routeManager = routeManager;
    }

    public void execute() {
        routeManager.sumOfDistance();
    }
}
