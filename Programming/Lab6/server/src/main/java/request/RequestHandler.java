package request;

import command.CommandInvoker;
import exception.CommandNotFoundException;
import response.Creator;
import response.ResponseCreator;
import route.Request;
import route.RequestType;
import route.Response;

public class RequestHandler {
    Creator responseCreator;
    CommandInvoker commandInvoker;

    public RequestHandler(CommandInvoker commandInvoker, Creator responseCreator) {
        this.commandInvoker = commandInvoker;
        this.responseCreator = responseCreator;
    }

    public Response handleRequest(Request request) throws CommandNotFoundException {
        if (request.getType().equals(RequestType.ROUTE_REQUEST)) {
            return handleRouteRequest(request);
        } else {
            return handleCommandRequest(request);
        }
    }

    public Response handleRouteRequest(Request request) throws CommandNotFoundException {
        if (commandInvoker.checkRouteRequirement(request.getUserString())) {
            return responseCreator.createResponse("", true, true);
        } else {
            return responseCreator.createResponse("", true, false);
        }
    }

    public Response handleCommandRequest(Request request) {
        commandInvoker.execute(request.getUserString(), request.getRoute());
        return responseCreator.createResponse();
    }
}

//клиент сначала отправляет объект запроса без класса route, затем, если он необходим,
//сервер об этом говорит и клиент повторяет запрос, только уже с собранным route
