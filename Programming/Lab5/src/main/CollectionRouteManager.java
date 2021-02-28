package main;

import exception.RouteBuildException;
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
            throws NumberFormatException, RouteBuildException;
    void addRoute(Route route);
    boolean addUniqueID(int id);
    void updateId(int id, Route route) throws NumberFormatException;
    Route createRoute(String name, Coordinates coordinates, FirstLocation from, SecondLocation to, double distance)
            throws NumberFormatException, RouteBuildException;
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
