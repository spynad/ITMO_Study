package csv;

import route.Route;

import java.util.ArrayList;
import java.util.Stack;

public interface Parser {
    ArrayList<Route> parseRouteFromFile(ArrayList<String> inputString);
    String makeFileFromRoute(Stack<Route> routes);
}
