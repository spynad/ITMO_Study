package command;

import exception.RouteBuildException;
import exception.RouteReadException;
import managers.*;
import exception.InvalidArgumentException;

import java.io.BufferedReader;

/**
 * Класс-команда, реализующая добавление элемента в коллекцию
 * @author spynad
 */
public class AddCommand implements Command{
    CollectionRouteManager routeManager;
    BufferedReader reader;

    AddCommand(CollectionRouteManager routeManager, BufferedReader reader) {
        this.routeManager = routeManager;
        this.reader = reader;
    }

    public void execute() {
        try {
            SingleRouteReader routeReader;
            if (reader == null) {
                routeReader = new ConsoleRouteReader(routeManager);
            } else {
                routeReader = new ScriptRouteReader(reader, routeManager);
            }

            routeManager.addRoute(routeReader.read());
        } catch (NumberFormatException | RouteReadException | RouteBuildException nfe) {
            System.err.println("invalid argument");
        } /* catch (RouteReadException eofe) {
            throw new IllegalStateException();
        }*/
    }
}
