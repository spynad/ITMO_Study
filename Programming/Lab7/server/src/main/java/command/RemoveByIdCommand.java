package command;

import collection.RouteCollectionManager;
import commands.AbstractCommand;
import commands.Command;
import exception.CommandExecutionException;
import exception.InvalidArgumentException;
import locale.ServerBundle;

/**
 * Класс-команда, реализующая удаление элемента коллекции по его ID
 */
public class RemoveByIdCommand extends AbstractCommand implements Command {
    private final RouteCollectionManager routeManager;
    private String[] args;

    public RemoveByIdCommand(RouteCollectionManager routeManager, boolean req) {
        super(req);
        this.routeManager = routeManager;
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args.clone();
    }

    public void execute() throws CommandExecutionException {
        try {
            if (args.length == 1) {
                routeManager.removeById(Integer.parseInt(args[0]));
            } else {
                throw new CommandExecutionException(String.format(ServerBundle.getString("exception.expected_got"),
                        1, args.length));
            }
        }  catch (NumberFormatException nfe) {
            throw new CommandExecutionException(ServerBundle.getString("exception.invalid_format_error"));
        }

    }
}
