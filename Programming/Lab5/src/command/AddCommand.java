package command;

import exception.RouteReadException;
import managers.CollectionRouteManager;
import managers.ConsoleRouteReader;
import managers.FileRouteReader;
import managers.RouteReader;
import route.Route;
import route.exceptions.InvalidArgumentException;

import java.io.BufferedReader;
import java.io.EOFException;

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
                routeReader = new ConsoleRouteReader();
            } else {
                routeReader = new FileRouteReader(reader);
            }

            routeManager.addRoute(routeReader.readName(),
                    routeReader.readCoordinates(),
                    routeReader.readFirstLocation(),
                    routeReader.readSecondLocation(),
                    routeReader.readDistance());
        } catch (NumberFormatException | InvalidArgumentException nfe) {
            System.err.println("invalid argument");
        } catch (RouteReadException eofe) {
            throw new IllegalStateException();
        }
    }
}
