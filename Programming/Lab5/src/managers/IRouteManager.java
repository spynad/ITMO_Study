package managers;

import route.Route;

import java.util.ArrayList;
import java.util.Stack;

public interface IRouteManager {
    Stack<Route> getRoutes();
    void addRoutes(ArrayList<Route> routes);
    void info();
}
