package managers;

import log.Log;
import route.Route;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;

public class RouteManager implements IRouteManager{
    private final Stack<Route> routes;
    private final LocalDate dateOfInit = LocalDate.now();

    public RouteManager() {
        Log.logger.log(Level.INFO,"RouteManager init");
        routes = new Stack<>();
    }

    public void addRoute() {
        //routes.add();
    }

    public void addRoutes(ArrayList<Route> routes) {
        Log.logger.log(Level.INFO,"Adding routes into stack");
        for (Route route : routes) {
            this.routes.push(route);
        }
    }

    public Stack<Route> getRoutes() {
        return routes;
    }

    public void info() {
        System.out.printf("%s, Date of init:%s, Number of objects: %d\n", routes.getClass().getName(),
                dateOfInit.toString(), routes.size());
    }
}
