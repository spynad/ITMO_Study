package managers;

import exception.InvalidArgumentException;
import exception.RouteReadException;
import route.Route;

import java.util.List;

public interface RouteReader {
    List<Route> read() throws InvalidArgumentException, RouteReadException;
}
