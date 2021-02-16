package command;

import managers.IRouteManager;
import route.Coordinates;
import route.FirstLocation;
import route.Route;
import route.SecondLocation;
import route.exceptions.InvalidArgumentException;

/**
 * Класс-команда, реализующая обновление элемента коллекции по его id
 */
public class UpdateCommand implements Command{
    IRouteManager routeManager;
    String[] args;

    UpdateCommand(IRouteManager routeManager) {
        this.routeManager = routeManager;
    }
    UpdateCommand(IRouteManager routeManager, String[] args) {
        this.routeManager = routeManager;
        this.args = args;
    }

    public void execute() {
        if(args.length > 4) {
            try {
                int id = Integer.parseInt(args[1]);
                Coordinates coordinates = new Coordinates(Long.parseLong(args[3]), Double.parseDouble(args[4]));
                FirstLocation firstLocation = new FirstLocation(Integer.parseInt(args[5]), Long.parseLong(args[6]), args[7]);
                SecondLocation secondLocation = new SecondLocation(Integer.parseInt(args[8]),
                        Long.parseLong(args[9]), Double.parseDouble(args[10]));
                Double.parseDouble(args[11]);


                routeManager.updateId(id, args[2], coordinates, firstLocation, secondLocation, Double.parseDouble(args[10]));
            } catch (NumberFormatException | InvalidArgumentException e) {
                System.err.println("invalid argument");
            }
        } else {

            try {
                routeManager.readCreateRoute(Integer.parseInt(args[1]));
                //routeManager.updateId(Integer.parseInt(args[1]), route);
            } catch (NumberFormatException | InvalidArgumentException nfe) {
                System.err.println("invalid argument");
            }
        }
    }
}
