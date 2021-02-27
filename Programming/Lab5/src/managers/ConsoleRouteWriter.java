package managers;

import route.Route;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class ConsoleRouteWriter implements SingleRouteWriter{
    CollectionRouteManager routeManager;

    public ConsoleRouteWriter(CollectionRouteManager routeManager) {
        this.routeManager = routeManager;
    }

    @Override
    public void write(Route route) {
        String from;
        if (route.getFrom() == null) {
            from = "null";
        } else {
            from = String.format("{x:%d, y:%d, name:\"%s\"}", route.getFrom().getX(), route.getFrom().getY(), route.getFrom().getName());
        }
        System.out.printf("Route object:ID:%d, Name:\"%s\", Coordinates: {x:%d, y:%.2f}, Creation date: %s" +
                        " from: %s, to: {x:%d, y:%d, z:%.2f}, Distance: %.2f%n", route.getId(),
                route.getName(), route.getCoordinates().getX(), route.getCoordinates().getY(),
                route.getCreationDate().toString(), from, route.getTo().getX(), route.getTo().getY(),
                route.getTo().getZ(), route.getDistance());
    }
}
