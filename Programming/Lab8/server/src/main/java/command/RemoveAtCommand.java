package command;

import collection.RouteCollectionManager;
import commands.AbstractCommand;
import commands.Command;
import exception.CommandExecutionException;
import exception.InvalidArgumentException;
import locale.ServerBundle;

/**
 * Класс-команда, реализующая удаление элемента на определенной позиции
 */
public class RemoveAtCommand extends AbstractCommand implements Command {
    private final RouteCollectionManager routeManager;
    private String[] args;

    public RemoveAtCommand(RouteCollectionManager routeManager, boolean req) {
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
                routeManager.removeAt(Integer.parseInt(args[0]));
            } else {
                throw new CommandExecutionException(String.format(ServerBundle.getString("exception.expected_got"),
                        1, args.length));
            }
        }  catch (NumberFormatException | CommandExecutionException nfe) {
            throw new CommandExecutionException(ServerBundle.getString("exception.invalid_format_error"));
        }
    }
}
