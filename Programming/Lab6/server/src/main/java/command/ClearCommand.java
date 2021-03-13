package command;

import commands.AbstractCommand;
import commands.Command;
import collection.RouteCollectionManager;

/**
 * Класс-команда, реализующая удаление всех элементов коллекции
 * @author spynad
 * @version govno
 */
public class ClearCommand extends AbstractCommand implements Command {
    RouteCollectionManager routeManager;

    public ClearCommand(RouteCollectionManager routeManager, boolean req) {
        super(req);
        this.routeManager = routeManager;
    }

    public void execute() {
        routeManager.clear();
    }
}
