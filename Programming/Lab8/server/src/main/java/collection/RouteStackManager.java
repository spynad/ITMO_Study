package collection;

import data.RouteDAO;
import locale.ServerBundle;
import response.Creator;
import route.*;
import server.UserAuthModule;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class RouteStackManager implements RouteCollectionManager {

    volatile private Stack<Route> routes;

    private final Creator creator;

    volatile private int lastId;

    private final LocalDate dateOfInit = LocalDate.now();

    private final RouteDAO routeDAO;

    private final UserAuthModule authModule;

    public RouteStackManager(Creator creator, RouteDAO routeDAO, UserAuthModule authModule) {
        routes = new Stack<>();
        this.creator = creator;
        this.routeDAO = routeDAO;
        this.authModule = authModule;
    }

    synchronized public void addRouteId(Route route) {
        route.setId(lastId++);
        routeDAO.insertRoute(route);
        routes.add(route);
    }


    synchronized public void addRoute(Route route) {
        routeDAO.insertRoute(route);
        routes.add(route);
    }

    synchronized public void addRoutesFromDB() {
        this.routes.addAll(routeDAO.selectRoutesToCollection());
        lastId = routeDAO.getNextId();
    }

    synchronized public int findRouteById(int id) {
        int index = 0;
        for (Route route : routes) {
            if (route.getId() == id) {
                return index;
            }
            index++;
        }
        return index;
    }

    synchronized public Stack<Route> getRoutes() {
        return routes;
    }

    synchronized public void info() {
        creator.addToMsg(String.format(ServerBundle.getString("collection.info"), routes.getClass().getName(),
                dateOfInit, routes.size()));
    }

    synchronized public void clear() {
        for (Iterator<Route> iterator = routes.iterator(); iterator.hasNext(); ) {
            Route route = iterator.next();
            if (route.getUsername().equals(authModule.getUser().getUsername())) {
                routeDAO.deleteRoute(route);
                iterator.remove();
            }
        }
    }

    synchronized public void removeAllByDistance(double distance) {
        for (Iterator<Route> iterator = routes.iterator(); iterator.hasNext(); ) {
            Route route = iterator.next();
            if (route.getDistance() == distance && route.getUsername().equals(authModule.getUser().getUsername())) {
                routeDAO.deleteRoute(route);
                iterator.remove();
            }
        }
    }

    synchronized public void sumOfDistance() {
        creator.addToMsg(String.format(ServerBundle.getString("collection.sum_of_distance"), routes.stream()
                .map(Route::getDistance)
                .reduce(Double::sum)
                .get()));
    }

    synchronized public void show() {
        /*RouteFormatter formatter = new RouteFormatter();
        routes.stream()
                .sorted()
                .forEach(x -> creator.addToMsg(formatter.formatRoute(x)));*/
        creator.setObject(routes);
    }

    synchronized public void removeFirst() {
        try {
            Route route = routes.get(0);
            if (route.getUsername().equals(authModule.getUser().getUsername())) {
                routeDAO.deleteRoute(route);
                routes.remove(0);
            }
        } catch (EmptyStackException | ArrayIndexOutOfBoundsException ese) {
            creator.addToMsg(ServerBundle.getString("collection.empty_stack"));
        }
    }

    synchronized public void removeById(int id) {
        for (int i = 0; i < routes.size(); i++) {
            if (routes.get(i).getId() == id && routes.get(i).getUsername().equals(authModule.getUser().getUsername())) {
                routeDAO.deleteRoute(routes.get(i));
                routes.remove(i);
                break;
            }
        }
    }

    synchronized public void removeAt(int index) {
        try {
            Route route = routes.get(index);
            if (route.getUsername().equals(authModule.getUser().getUsername())) {
                routeDAO.deleteRoute(route);
                routes.remove(index);
                creator.addToMsg(ServerBundle.getString("collection.removed_one"));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            creator.addToMsg(ServerBundle.getString("collection.unexistent_element"));
        }
    }

    synchronized public void filterContainsName(String name) {
        RouteFormatter routeFormatter = new RouteFormatter();
        routes.stream()
                .filter(x -> x.getName().contains(name))
                .forEach(x -> creator.addToMsg(routeFormatter.formatRoute(x)));
    }


    synchronized public void updateId(int id, Route route)
            throws NumberFormatException{
        int index = findRouteById(id);
        if(routes.get(index).getUsername().equals(authModule.getUser().getUsername())) {
            route.setId(id);
            routeDAO.updateRoute(route);
            routes.set(index, route);
        }
    }
}
