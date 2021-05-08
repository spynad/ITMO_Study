package route;

import exception.RouteBuildException;

import java.time.LocalDate;

public interface Builder {
    RouteBuilder setId(int id) throws RouteBuildException;
    RouteBuilder setName(String name) throws RouteBuildException;
    RouteBuilder setCoordinates(Coordinates coordinates) throws RouteBuildException;
    RouteBuilder setDate(LocalDate localDate) throws RouteBuildException;
    RouteBuilder setFirstLocation(FirstLocation firstLocation) throws RouteBuildException;
    RouteBuilder setSecondLocation(SecondLocation secondLocation) throws RouteBuildException;
    RouteBuilder setDistance(double distance) throws RouteBuildException;
    RouteBuilder setUsername(String username) throws RouteBuildException;
    Route buildWithId();
    Route buildWithoutId();
}
