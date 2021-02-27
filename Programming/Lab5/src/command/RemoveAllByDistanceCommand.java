package command;

import managers.CollectionRouteManager;
import exception.InvalidArgumentException;


/**
 * Класс-команда, реализующая удаление всех элементов коллекции, у которых поле distance=заданному
 */
public class RemoveAllByDistanceCommand implements Command{
    CollectionRouteManager routeManager;
    String[] args;

    RemoveAllByDistanceCommand(CollectionRouteManager routeManager, String[] args) {
        this.routeManager = routeManager;
        this.args = args;
    }

    public void execute() {
        try {
            if (args.length == 1) {
                routeManager.removeAllByDistance(Double.parseDouble(args[0]));
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
