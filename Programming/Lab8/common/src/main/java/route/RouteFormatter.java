package route;

public class RouteFormatter {
    public String formatRoute(Route route) {
        String from;
        if (route.getFrom() == null) {
            from = "null";
        } else {
            from = String.format("{x:%d, y:%d, name:\"%s\"}", route.getFrom().getX(), route.getFrom().getY(), route.getFrom().getName());
        }
        return String.format("Route object:ID:%d, Name:\"%s\", Coordinates: {x:%d, y:%.2f}, Creation date: %s" +
                        " from: %s, to: {x:%d, y:%d, z:%.2f}, Distance: %.2f, Belongs to: %s", route.getId(),
                route.getName(), route.getCoordinates().getX(), route.getCoordinates().getY(),
                route.getCreationDate().toString(), from, route.getTo().getX(), route.getTo().getY(),
                route.getTo().getZ(), route.getDistance(), route.getUsername());
    }
}
