package command;

import managers.IRouteManager;
import route.Coordinates;
import route.FirstLocation;
import route.Route;
import route.SecondLocation;
import route.exceptions.InvalidArgumentException;

/**
 * Класс-команда, реализующая добавление элемента в коллекцию
 * @author spynad
 * @version govno
 */
public class AddCommand implements Command{
    IRouteManager routeManager;
    String[] args;

    AddCommand(IRouteManager routeManager) {
        this.routeManager = routeManager;
    }
    AddCommand(IRouteManager routeManager, String[] args) {
        this.routeManager = routeManager;
        this.args = args;
    }

    public void execute() {
        if(args.length > 3) {
            try {
                Coordinates coordinates = new Coordinates(Long.parseLong(args[2]), Double.parseDouble(args[3]));
                FirstLocation firstLocation = new FirstLocation(Integer.parseInt(args[4]), Long.parseLong(args[5]), args[6]);
                SecondLocation secondLocation = new SecondLocation(Integer.parseInt(args[7]),
                        Long.parseLong(args[8]), Double.parseDouble(args[9]));
                Double.parseDouble(args[10]);

                routeManager.addRoute(args[1], coordinates, firstLocation, secondLocation, Double.parseDouble(args[10]));
            } catch (NumberFormatException | InvalidArgumentException e) {
                System.err.println("invalid argument");
            }
        } else {
            try {
                Route route = routeManager.readRoute(args[1], args[2], -1);
                routeManager.addRoute(route);
            } catch (NumberFormatException | InvalidArgumentException nfe) {
                System.err.println("invalid argument");
            }
        }
    }
}
