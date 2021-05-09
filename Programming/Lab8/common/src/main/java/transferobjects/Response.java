package transferobjects;

import java.io.Serializable;
import java.nio.channels.SocketChannel;

public class Response implements Serializable {
    private static final long serialVersionUID = -5622461857486946378L;

    private String message;
    private boolean success;
    private boolean routeRequired;
    private Object obj;
    private transient SocketChannel socketChannel;

    public Response(String message, boolean b, boolean b2) {
        this.message = message;
        this.success = b;
        this.routeRequired = b2;
    }

    public Response() {
        message = "";
        success = true;
        routeRequired = false;
    }

    public String getMessage() {
        return message;
    }

    public Object getObj() {
        return obj;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isRouteRequired() {
        return routeRequired;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(boolean b) {
        this.success = b;
    }

    public void setRouteRequired(boolean routeRequired) {
        this.routeRequired = routeRequired;
    }

    public void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
