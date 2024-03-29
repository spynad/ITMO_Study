package transferobjects;

import route.Route;
import user.User;

import java.io.Serializable;
import java.nio.channels.SocketChannel;
import java.util.Locale;

public class Request implements Serializable {
    private static final long serialVersionUID = -4287447999382808577L;

    private RequestType type;
    private RequestOpsState opsState;
    private String userString;
    private Route route;
    private User user;
    private Locale locale = Locale.getDefault();
    private transient SocketChannel socketChannel;

    public Request(RequestType type, String userString, Route route) {
        this.type = type;
        this.userString = userString;
        this.route = route;
    }

    public Request(RequestType type, User user) {
        this.type = type;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public Locale getLocale() {
        return locale;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public void setType(RequestType type) {
        this.type = type;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }
}
