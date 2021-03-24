package command;

import collection.RouteCollectionManager;
import commands.AbstractCommand;
import commands.Command;

/**
 * Класс-команда, реализующая вывод в стандартный поток всей коллекциииии
 */
public class ShowCommand extends AbstractCommand implements Command {
    private final RouteCollectionManager routeManager;

    public ShowCommand(RouteCollectionManager routeManager, boolean req) {
        super(req);
        this.routeManager = routeManager;
    }

    public void execute() {
        routeManager.show();
    }
}
