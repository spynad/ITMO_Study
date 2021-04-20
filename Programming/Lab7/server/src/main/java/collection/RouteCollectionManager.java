package collection;

import route.Route;

import java.util.Collection;
import java.util.Stack;

public interface RouteCollectionManager {
    Stack<Route> getRoutes();
    void addRoute(Route route);
    void addRouteId(Route route);
    void addRoutes(Collection<Route> routes);
    boolean addUniqueID(int id);
    void updateId(int id, Route route) throws NumberFormatException;
    void info();
    void clear();
    void removeAllByDistance(double distance);
    void sumOfDistance();
    void show();
    void removeFirst();
    void removeById(int id);
    void removeAt(int index);
    void filterContainsName(String name);
}
