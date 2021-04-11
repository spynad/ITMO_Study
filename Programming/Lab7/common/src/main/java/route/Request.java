package route;

import java.io.Serializable;
import java.util.Arrays;

public class Request implements Serializable {
    private static final long serialVersionUID = -4287447999382808577L;

    private RequestType type;
    private String userString;
    private Route route;

    public Request(RequestType type, String userString, Route route) {
        this.type = type;
        this.userString = userString;
        this.route = route;
    }

    public String getUserString() {
        return userString;
    }

    public Route getRoute() {
        return route;
    }


    public RequestType getType() {
        return type;
    }

    public void setUserString(String userString) {
        this.userString = userString;
    }

    public void setType(RequestType type) {
        this.type = type;
    }

    public void setRoute(Route route) {
        this.route = route;
    }


    @Override
    public String toString() {
        return "Request{" +
                "commandName='" + userString + '\'' +
                ", route=" + route +
                '}';
    }
}
