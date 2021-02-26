package command;

import exception.RouteReadException;
import managers.CollectionRouteManager;
import managers.ConsoleRouteReader;
import managers.FileRouteReader;
import managers.RouteReader;
import route.Coordinates;
import route.FirstLocation;
import route.SecondLocation;
import route.exceptions.InvalidArgumentException;

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
                RouteReader routeReader;
                if (reader == null) {
                    routeReader = new ConsoleRouteReader();
                } else {
                    routeReader = new FileRouteReader(reader);
                }
                routeManager.updateId(Integer.parseInt(args[0]), routeReader.readName(),
                        routeReader.readCoordinates(),
                        routeReader.readFirstLocation(),
                        routeReader.readSecondLocation(),
                        routeReader.readDistance());
            } else {
                throw new InvalidArgumentException("expected 1 argument, got " + args.length);
            }
        } catch (NumberFormatException nfe) {
            System.err.println("incorrect argument format");
        } catch (InvalidArgumentException iae) {
            System.err.println(iae.getMessage());
        } catch (RouteReadException rre) {
            throw new IllegalStateException();
        }
    }
}
