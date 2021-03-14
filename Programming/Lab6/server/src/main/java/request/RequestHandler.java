package request;

import command.CommandInvoker;
import exception.CommandExecutionException;
import exception.CommandNotFoundException;
import log.Log;
import response.Creator;
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

    public Response handleRequest(Request request) throws CommandNotFoundException, CommandExecutionException {
        if (request.getType().equals(RequestType.ROUTE_REQUEST)) {
            return handleRouteRequest(request);
        } else {
            return handleCommandRequest(request);
        }
    }

    public Response handleRouteRequest(Request request) throws CommandNotFoundException {
        Log.getLogger().info("Client is asking for route requirement");
        if (commandInvoker.checkRouteRequirement(request.getUserString())) {
            Log.getLogger().info("Creating response with positive answer");
            return responseCreator.createResponse("", true, true);
        } else {
            Log.getLogger().info("Creating response with negative answer");
            return responseCreator.createResponse("", true, false);
        }
    }

    public Response handleCommandRequest(Request request) throws CommandExecutionException, CommandNotFoundException {
        Log.getLogger().info("Attempt to execute " + request.getUserString());
        commandInvoker.execute(request.getUserString(), request.getRoute());
        return responseCreator.createResponse();
    }
}

//клиент сначала отправляет объект запроса без класса route, затем, если он необходим,
//сервер об этом говорит и клиент повторяет запрос, только уже с собранным route
