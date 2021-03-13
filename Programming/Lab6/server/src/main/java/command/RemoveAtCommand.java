package command;

import collection.RouteCollectionManager;
import commands.AbstractCommand;
import commands.Command;
import exception.CommandExecutionException;
import exception.InvalidArgumentException;

/**
 * Класс-команда, реализующая удаление элемента на определенной позиции
 */
public class RemoveAtCommand extends AbstractCommand implements Command {
    RouteCollectionManager routeManager;
    String[] args;

    public RemoveAtCommand(RouteCollectionManager routeManager, boolean req) {
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
                routeManager.removeAt(Integer.parseInt(args[0]));
            } else {
                throw new CommandExecutionException("expected 1 argument, got " + args.length);
            }
        }  catch (NumberFormatException | CommandExecutionException nfe) {
            throw new CommandExecutionException("incorrect argument format");
        }
    }
}
