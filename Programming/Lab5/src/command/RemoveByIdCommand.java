package command;

import managers.CollectionRouteManager;
import route.exceptions.InvalidArgumentException;

/**
 * Класс-команда, реализующая удаление элемента коллекции по его ID
 */
public class RemoveByIdCommand implements Command{
    CollectionRouteManager routeManager;
    String[] args;

    RemoveByIdCommand(CollectionRouteManager routeManager, String[] args) {
        this.routeManager = routeManager;
        this.args = args;
    }
    public void execute() {
        try {
            if (args.length == 1) {
                routeManager.removeById(Integer.parseInt(args[0]));
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
