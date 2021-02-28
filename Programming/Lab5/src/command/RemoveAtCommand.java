package command;

import main.CollectionRouteManager;
import exception.InvalidArgumentException;

/**
 * Класс-команда, реализующая удаление элемента на определенной позиции
 */
public class RemoveAtCommand implements Command{
    CollectionRouteManager routeManager;
    String[] args;

    RemoveAtCommand(CollectionRouteManager routeManager, String[] args) {
        this.routeManager = routeManager;
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
