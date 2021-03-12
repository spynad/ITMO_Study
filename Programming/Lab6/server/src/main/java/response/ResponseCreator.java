package response;

import route.Response;

public class ResponseCreator implements Creator{
    Response response = new Response();

    public Response createResponse(String message, boolean success, boolean routeRequired) {
        return new Response(message, success, routeRequired);
    }

    public Response createResponse() {
        Response response = this.response;
        this.response = new Response();
        return response;
    }

    public void addToMsg(String msg) {
        String initial = response.getMessage();
        response.setMessage(initial + msg);
    }
}
