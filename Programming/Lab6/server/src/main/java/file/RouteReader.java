package file;

import exception.InvalidArgumentException;
import exception.RouteBuildException;
import exception.RouteReadException;
import route.Route;

import java.util.List;

public interface RouteReader {
    List<Route> read() throws InvalidArgumentException, RouteReadException, RouteBuildException;
}
