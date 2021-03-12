package command;

import exception.RouteBuildException;
import exception.RouteReadException;
import io.UserIO;
import main.RouteCollectionManager;
import main.ConsoleRouteParser;
import main.ScriptRouteParser;
import main.SingleRouteReader;

import java.io.BufferedReader;

/**
 * Класс-команда, реализующая добавление элемента в коллекцию
 * @author spynad
 */
public class AddCommand implements Command{
    RouteCollectionManager routeManager;
    UserIO userIO;
    BufferedReader reader;

    AddCommand(RouteCollectionManager routeManager, BufferedReader reader, UserIO userIO) {
        this.routeManager = routeManager;
        this.reader = reader;
        this.userIO = userIO;
    }

    public void execute() {
        try {
            SingleRouteReader routeReader;
            if (reader == null) {
                routeReader = new ConsoleRouteParser(routeManager, userIO);
            } else {
                routeReader = new ScriptRouteParser(reader, routeManager);
            }

            routeManager.addRoute(routeReader.read());
        } catch (NumberFormatException | RouteReadException | RouteBuildException nfe) {
            System.err.println("invalid argument");
        } /* catch (RouteReadException eofe) {
            throw new IllegalStateException();
        }*/
    }
}
