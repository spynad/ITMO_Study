package response;

import route.Response;

public interface Creator {
    public Response createResponse(String message, boolean success, boolean routeRequired);

    public Response createResponse();

    public void addToMsg(String msg);
}
