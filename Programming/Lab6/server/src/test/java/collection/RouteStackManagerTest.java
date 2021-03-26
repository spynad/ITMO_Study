package collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import response.ResponseCreator;
import route.*;

import java.util.Stack;

class RouteStackManagerTest {
    private static final RouteStackManager manager = new RouteStackManager(new ResponseCreator());
    private static Route routeTest;

    @BeforeAll
    public static void prepareTestData() {
        routeTest = new Route("name", new Coordinates(100L, 100.0), new FirstLocation(100, 100L, "name"),
                new SecondLocation(100, 100L, 100.0), 100.0);
    }

    @Test
    public void addRouteId() {
        Stack<Route> routes = new Stack<>();
        manager.addRouteId(routeTest);
        routes.add(routeTest);
        Assertions.assertEquals(manager.getRoutes(), routes);
        Assertions.assertEquals(manager.getRoutes().get(0).getId(), 1);
    }

}