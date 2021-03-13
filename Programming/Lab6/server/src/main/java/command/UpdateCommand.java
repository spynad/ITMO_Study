package command;

import collection.RouteCollectionManager;
import commands.AbstractCommand;
import commands.RouteCommand;
import exception.CommandExecutionException;
import route.Route;

import java.io.BufferedReader;

/**
 * Класс-команда, реализующая обновление элемента коллекции по его id
 */
public class UpdateCommand extends AbstractCommand implements RouteCommand {
    RouteCollectionManager routeManager;
    String[] args;

    public UpdateCommand(RouteCollectionManager routeManager, boolean req) {
        super(req);
        this.routeManager = routeManager;
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args;
    }

    public void execute() {
        throw new UnsupportedOperationException();
    }

    public void execute(Route route) throws CommandExecutionException {
        try {
            if (args.length == 1) {
                routeManager.updateId(Integer.parseInt(args[0]), route);
            } else {
                throw new CommandExecutionException("expected 1 argument, got " + args.length);
            }
        } catch (NumberFormatException nfe) {
            throw new CommandExecutionException("incorrect argument format");
        }
    }
}
