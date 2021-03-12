package command;

import exception.RouteBuildException;
import exception.RouteReadException;
import io.UserIO;
import main.RouteCollectionManager;
import main.ConsoleRouteParser;
import main.ScriptRouteParser;
import main.SingleRouteReader;
import exception.InvalidArgumentException;

import java.io.BufferedReader;

/**
 * Класс-команда, реализующая обновление элемента коллекции по его id
 */
public class UpdateCommand implements Command{
    RouteCollectionManager routeManager;
    UserIO userIO;
    BufferedReader reader;
    String[] args;

    UpdateCommand(RouteCollectionManager routeManager, BufferedReader reader, UserIO userIO) {
        this.routeManager = routeManager;
        this.reader = reader;
        this.userIO = userIO;
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args;
    }

    public void execute() {
        try {
            if (args.length == 1) {
                SingleRouteReader routeReader;
                if (reader == null) {
                    routeReader = new ConsoleRouteParser(routeManager, userIO);
                } else {
                    routeReader = new ScriptRouteParser(reader, routeManager);
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
