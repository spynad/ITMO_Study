package collection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import response.ResponseCreator;
import route.*;

import java.util.Stack;

class RouteStackManagerTest {
    /*private static final RouteStackManager manager = new RouteStackManager(new ResponseCreator());

    @AfterEach
    public void emptyCollection() {
        manager.clear();
    }

    @Test
    public void addRouteId() {
        Route routeTest = new Route("name", new Coordinates(100L, 100.0), new FirstLocation(100, 100L, "name"),
                new SecondLocation(100, 100L, 100.0), 100.0);
        Stack<Route> routes = new Stack<>();
        manager.addRouteId(routeTest);
        routes.add(routeTest);
        Assertions.assertEquals(routes, manager.getRoutes());
        Assertions.assertEquals(1, manager.getRoutes().get(0).getId());
    }

    @Test
    public void addRouteId_DELETEMULTIPLE() {
        manager.addRouteId(new Route("name", new Coordinates(100L, 100.0), new FirstLocation(100, 100L, "name"),
                new SecondLocation(100, 100L, 100.0), 100.0));
        manager.addRouteId(new Route("name", new Coordinates(100L, 100.0), new FirstLocation(100, 100L, "name"),
                new SecondLocation(100, 100L, 100.0), 100.0));
        manager.addRouteId(new Route("name", new Coordinates(100L, 100.0), new FirstLocation(100, 100L, "name"),
                new SecondLocation(100, 100L, 100.0), 100.0));
        Assertions.assertEquals(1, manager.getRoutes().get(0).getId());
        Assertions.assertEquals(2, manager.getRoutes().get(1).getId());
        Assertions.assertEquals(3, manager.getRoutes().get(2).getId());
        manager.removeFirst();
        manager.addRouteId(new Route("name", new Coordinates(100L, 100.0), new FirstLocation(100, 100L, "name"),
                new SecondLocation(100, 100L, 100.0), 100.0));
        Assertions.assertEquals(2, manager.getRoutes().get(0).getId());
        Assertions.assertEquals(3, manager.getRoutes().get(1).getId());
        Assertions.assertEquals(1, manager.getRoutes().get(2).getId());
    }

    @Test
    public void testRemovalMethods() {
        Route route100_1 = new Route("name", new Coordinates(100L, 100.0), new FirstLocation(100, 100L, "name"),
                new SecondLocation(100, 100L, 100.0), 100.0);
        Route route100_2 = new Route("name", new Coordinates(100L, 100.0), new FirstLocation(100, 100L, "name"),
                new SecondLocation(100, 100L, 100.0), 100.0);
        Route route101 = new Route("name", new Coordinates(100L, 100.0), new FirstLocation(100, 100L, "name"),
                new SecondLocation(100, 100L, 100.0), 101.0);
        Route route102 = new Route("name", new Coordinates(100L, 100.0), new FirstLocation(100, 100L, "name"),
                new SecondLocation(100, 100L, 100.0), 102.0);
        Route route103_id1000 = new Route(1000, "name", new Coordinates(100L, 100.0), new FirstLocation(100, 100L, "name"),
                new SecondLocation(100, 100L, 100.0), 103.0);
        manager.addRouteId(route100_1);
        manager.addRouteId(route100_2);
        manager.addRouteId(route101);
        manager.addRouteId(route102);
        manager.addRoute(route103_id1000);
        Stack<?> routes = (Stack<?>) manager.getRoutes().clone();
        manager.removeAllByDistance(100.0);
        routes.remove(0);
        routes.remove(0);
        Assertions.assertEquals(routes, manager.getRoutes());
        manager.addRouteId(route100_1);
        manager.addRouteId(route100_2);
        routes = (Stack<?>) manager.getRoutes().clone();
        manager.removeFirst();
        routes.remove(0);
        Assertions.assertEquals(routes, manager.getRoutes());
        manager.addRouteId(route100_1);
        routes = (Stack<?>) manager.getRoutes().clone();
        manager.removeById(1000);
        routes.remove(route103_id1000);
        Assertions.assertEquals(routes, manager.getRoutes());
        manager.removeAt(0);
        routes.remove(0);
        Assertions.assertEquals(routes, manager.getRoutes());
    }*/

}