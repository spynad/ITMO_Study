package request;

import client.AuthModule;
import transferobjects.Request;
import transferobjects.RequestType;
import route.Route;

public class RequestCreator {
    private final AuthModule authModule;

    public RequestCreator(AuthModule authModule) {
        this.authModule = authModule;
    }

    public Request createRouteRequest(String userString) {
        Request request = new Request(RequestType.ROUTE_REQUEST, userString, null);
        request.setUser(authModule.getUser());
        return request;
    }

    public Request createCommandRequest(String userString, Route route) {
        Request request = new Request(RequestType.COMMAND_REQUEST, userString, route);
        request.setUser(authModule.getUser());
        return request;
    }
}
