package command;

import collection.RouteCollectionManager;
import commands.AbstractCommand;
import commands.Command;

/**
 * Класс-команда, реализующая удаление первого элемента коллекции
 */
public class RemoveFirstCommand extends AbstractCommand implements Command {
    RouteCollectionManager routeManager;

    public RemoveFirstCommand(RouteCollectionManager routeManager, boolean req) {
        super(req);
        this.routeManager = routeManager;
    }
    public void execute() {
        routeManager.removeFirst();
    }
}
