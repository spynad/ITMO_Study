package command;

import collection.RouteCollectionManager;
import commands.AbstractCommand;
import commands.Command;

/**
 * Класс-команда, реализующая вывод информации о коллекции
 */
public class InfoCommand extends AbstractCommand implements Command {
    private final RouteCollectionManager routeManager;

    public InfoCommand(RouteCollectionManager routeManager, boolean req) {
        super(req);
        this.routeManager = routeManager;
    }

    public void execute() {
        routeManager.info();
    }
}
