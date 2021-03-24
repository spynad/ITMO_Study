package command;

import commands.AbstractCommand;
import commands.RouteCommand;
import collection.RouteCollectionManager;
import route.Route;


/**
 * Класс-команда, реализующая добавление элемента в коллекцию
 * @author spynad
 */
public class AddCommand extends AbstractCommand implements RouteCommand {
    private final RouteCollectionManager routeManager;

    public AddCommand(RouteCollectionManager routeManager, boolean req) {
        super(req);
        this.routeManager = routeManager;
    }

    public void execute() {
        throw new UnsupportedOperationException();
    }

    public void execute(Route route) {
        routeManager.addRoute(route);
    }
}
