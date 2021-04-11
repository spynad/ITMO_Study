package command;

import collection.RouteCollectionManager;
import commands.AbstractCommand;
import commands.Command;
import exception.CommandExecutionException;
import exception.InvalidArgumentException;
import locale.ServerBundle;

/**
 * Класс-команда, реализующая поиск и вывод элементов фыващзш
 */
public class FilterContainsNameCommand extends AbstractCommand implements Command {
    private final RouteCollectionManager routeManager;
    private String[] args;

    public FilterContainsNameCommand(RouteCollectionManager routeManager, boolean req) {
        super(req);
        this.routeManager = routeManager;
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args.clone();
    }

    public void execute() throws CommandExecutionException{
        try {
            if (args.length == 1) {
                routeManager.filterContainsName(args[0]);
            } else {
                throw new InvalidArgumentException(String.format(ServerBundle.getString("exception.expected_got"),
                        1, args.length));
            }
        } catch (InvalidArgumentException iae) {
            throw new CommandExecutionException(iae.getMessage());
        }

    }
}
