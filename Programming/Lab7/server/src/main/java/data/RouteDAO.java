package data;

import route.Route;

import java.util.Collection;

public interface RouteDAO {
    Collection<Route> selectRoutesToCollection();
    Route getRoute(int id);
    void insertRoute(Route route);
    boolean updateRoute(Route route);
    boolean deleteRoute(Route route);
    boolean deleteRoutes();
}
