package request;

import route.Request;
import route.RequestType;
import route.Route;

public class RequestCreator {
    public Request createRouteRequest(String userString) {
        return new Request(RequestType.ROUTE_REQUEST, userString, null);
    }

    public Request createCommandRequest(String userString, Route route) {
        return new Request(RequestType.COMMAND_REQUEST, userString, route);
    }
}
