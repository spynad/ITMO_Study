package managers;

import route.Coordinates;
import route.FirstLocation;
import route.Route;
import route.SecondLocation;
import exception.InvalidArgumentException;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public interface CollectionRouteManager {
    Stack<Route> getRoutes();
    void addRoutes(List<Route> routes);
    void addRoute(String name, Coordinates coordinates, FirstLocation from, SecondLocation to, double distance)
            throws InvalidArgumentException, NumberFormatException;
    boolean addUniqueID(int id);
    void updateId(int id, List<Route> route) throws InvalidArgumentException, NumberFormatException;
    Route createRoute(String name, Coordinates coordinates, FirstLocation from, SecondLocation to, double distance)
            throws InvalidArgumentException, NumberFormatException;
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
