package command;

import exception.RouteBuildException;
import exception.RouteReadException;
import managers.*;
import exception.InvalidArgumentException;

import java.io.BufferedReader;

/**
 * Класс-команда, реализующая обновление элемента коллекции по его id
 */
public class UpdateCommand implements Command{
    CollectionRouteManager routeManager;
    BufferedReader reader;
    String[] args;

    UpdateCommand(CollectionRouteManager routeManager, String[] args, BufferedReader reader) {
        this.routeManager = routeManager;
        this.args = args;
        this.reader = reader;
    }

    public void execute() {
        try {
            if (args.length == 1) {
                SingleRouteReader routeReader;
                if (reader == null) {
                    routeReader = new ConsoleRouteReader(routeManager);
                } else {
                    routeReader = new ScriptRouteReader(reader, routeManager);
                }
                routeManager.updateId(Integer.parseInt(args[0]), routeReader.read());
            } else {
                throw new InvalidArgumentException("expected 1 argument, got " + args.length);
            }
        } catch (NumberFormatException nfe) {
            System.err.println("incorrect argument format");
        } catch (InvalidArgumentException | RouteBuildException iae) {
            System.err.println(iae.getMessage());
        } catch (RouteReadException rre) {
            throw new IllegalStateException();
        }
    }
}
