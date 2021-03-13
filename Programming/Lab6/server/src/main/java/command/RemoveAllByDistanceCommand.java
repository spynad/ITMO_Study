package command;

import collection.RouteCollectionManager;
import commands.AbstractCommand;
import commands.Command;
import exception.CommandExecutionException;
import exception.InvalidArgumentException;


/**
 * Класс-команда, реализующая удаление всех элементов коллекции, у которых поле distance=заданному
 */
public class RemoveAllByDistanceCommand extends AbstractCommand implements Command {
    RouteCollectionManager routeManager;
    String[] args;

    public RemoveAllByDistanceCommand(RouteCollectionManager routeManager, boolean req) {
        super(req);
        this.routeManager = routeManager;
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args;
    }

    public void execute() throws CommandExecutionException {
        try {
            if (args.length == 1) {
                routeManager.removeAllByDistance(Double.parseDouble(args[0]));
            } else {
                throw new CommandExecutionException("expected 1 argument, got " + args.length);
            }
        } catch (NumberFormatException nfe) {
            throw new CommandExecutionException("incorrect argument format");
        }

    }
}
