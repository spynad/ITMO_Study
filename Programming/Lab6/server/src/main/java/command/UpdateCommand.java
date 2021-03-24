package command;

import collection.RouteCollectionManager;
import commands.AbstractCommand;
import commands.RouteCommand;
import exception.CommandExecutionException;
import locale.ServerBundle;
import route.Route;

/**
 * Класс-команда, реализующая обновление элемента коллекции по его id
 */
public class UpdateCommand extends AbstractCommand implements RouteCommand {
    private final RouteCollectionManager routeManager;
    private String[] args;

    public UpdateCommand(RouteCollectionManager routeManager, boolean req) {
        super(req);
        this.routeManager = routeManager;
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args.clone();
    }

    public void execute() {
        throw new UnsupportedOperationException();
    }

    public void execute(Route route) throws CommandExecutionException {
        try {
            if (args.length == 1) {
                routeManager.updateId(Integer.parseInt(args[0]), route);
            } else {
                throw new CommandExecutionException(String.format(ServerBundle.getString("exception.expected_got"),
                        1, args.length));
            }
        } catch (NumberFormatException nfe) {
            throw new CommandExecutionException(ServerBundle.getString("exception.invalid_format_error"));
        }
    }
}
