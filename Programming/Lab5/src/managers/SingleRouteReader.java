package managers;

import exception.RouteBuildException;
import exception.RouteReadException;
import route.Route;

public interface SingleRouteReader {
    Route read() throws RouteBuildException, RouteReadException;
}
