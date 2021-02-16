package managers;

import route.Coordinates;
import route.FirstLocation;
import route.Route;
import route.SecondLocation;
import route.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.Set;
import java.util.Stack;

public interface IRouteManager {
    Stack<Route> getRoutes();
    void addRoutes(ArrayList<Route> routes);
    void addRoute(String name, Coordinates coordinates, FirstLocation from, SecondLocation to, double distance) throws InvalidArgumentException;
    void addRoute(Route route);
    boolean addUniqueID(int id);
    void readCreateRoute(int id) throws InvalidArgumentException;
    Set<Integer> getUniqueID();
    void info();
    void clear();
    void removeAllByDistance(double distance);
    void sumOfDistance();
    void show();
    void removeFirst();
    void removeById(int id);
    void removeAt(int index);
    void filterContainsName(String name);
    //void readRoute(int id) throws InvalidArgumentException;
    void updateId(int id, String name, Coordinates coordinates, FirstLocation from, SecondLocation to, double distance);
    void updateId(int id, Route route);
}
