package command;

import exception.RouteReadException;
import managers.CollectionRouteManager;
import managers.ConsoleRouteReader;
import managers.ScriptRouteReader;
import managers.RouteReader;
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
            RouteReader routeReader;
            if (reader == null) {
                routeReader = new ConsoleRouteReader(routeManager);
            } else {
                routeReader = new ScriptRouteReader(reader, routeManager);
            }

            routeManager.addRoutes(routeReader.read());
        } catch (NumberFormatException | InvalidArgumentException | RouteReadException nfe) {
            System.err.println("invalid argument");
        } /* catch (RouteReadException eofe) {
            throw new IllegalStateException();
        }*/
    }
}
