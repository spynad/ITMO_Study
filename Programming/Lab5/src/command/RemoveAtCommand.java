package command;

import main.RouteCollectionManager;
import exception.InvalidArgumentException;

/**
 * Класс-команда, реализующая удаление элемента на определенной позиции
 */
public class RemoveAtCommand implements Command{
    RouteCollectionManager routeManager;
    String[] args;

    RemoveAtCommand(RouteCollectionManager routeManager) {
        this.routeManager = routeManager;
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args;
    }

    public void execute() {
        try {
            if (args.length == 1) {
                routeManager.removeAt(Integer.parseInt(args[0]));
            } else {
                throw new InvalidArgumentException("expected 1 argument, got " + args.length);
            }
        } catch (InvalidArgumentException iae) {
            System.err.println(iae.getMessage());
        } catch (NumberFormatException nfe) {
            System.err.println("incorrect argument format");
        }
    }
}
