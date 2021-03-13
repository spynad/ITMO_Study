package command;

import collection.RouteCollectionManager;
import commands.AbstractCommand;
import commands.Command;
import exception.CommandExecutionException;
import exception.InvalidArgumentException;

/**
 * Класс-команда, реализующая поиск и вывод элементов фыващзш
 */
public class FilterContainsNameCommand extends AbstractCommand implements Command {
    RouteCollectionManager routeManager;
    String[] args;

    public FilterContainsNameCommand(RouteCollectionManager routeManager, boolean req) {
        super(req);
        this.routeManager = routeManager;
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args;
    }

    public void execute() throws CommandExecutionException{
        try {
            if (args.length == 1) {
                routeManager.filterContainsName(args[0]);
            } else {
                throw new InvalidArgumentException("expected 1 argument, got" + args.length);
            }
        } catch (InvalidArgumentException iae) {
            throw new CommandExecutionException(iae.getMessage());
        }

    }
}
