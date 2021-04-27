package request;

import command.CommandInvoker;
import exception.AuthException;
import exception.CommandExecutionException;
import exception.CommandNotFoundException;
import locale.ServerBundle;
import log.Log;
import response.Creator;
import transferobjects.Request;
import transferobjects.RequestType;
import transferobjects.Response;
import server.UserAuthModule;

import java.util.Locale;

public class RequestHandlerImpl implements RequestHandler{
    private final Creator responseCreator;
    private final CommandInvoker commandInvoker;
    private final UserAuthModule userAuthModule;

    public RequestHandlerImpl(CommandInvoker commandInvoker, Creator responseCreator, UserAuthModule userAuthModule) {
        this.commandInvoker = commandInvoker;
        this.responseCreator = responseCreator;
        this.userAuthModule = userAuthModule;
    }

    public Response handleRequest(Request request) throws CommandNotFoundException, CommandExecutionException, AuthException {
        Locale.setDefault(request.getLocale());
        if (request.getType().equals(RequestType.ROUTE_REQUEST)) {
            return handleRouteRequest(request);
        } else if (request.getType().equals(RequestType.COMMAND_REQUEST)){
            return handleCommandRequest(request);
        } else if (request.getType().equals(RequestType.AUTH_REQUEST)){
            return handleAuthRequest(request);
        } else {
            return handleRegisterRequest(request);
        }
    }

    private Response handleAuthRequest(Request request) {
        boolean result = userAuthModule.authUser(request.getUser());
        return responseCreator.createResponse(userAuthModule.getReason(), result, result);
    }

    private Response handleRegisterRequest(Request request) {
        boolean result = userAuthModule.registerUser(request.getUser());
        return responseCreator.createResponse(userAuthModule.getReason(), result, result);
    }

    private Response handleRouteRequest(Request request) throws CommandNotFoundException, AuthException {
        //TODO: разъединить условие (в след методе тоже)
        if (request.getUser() == null || !userAuthModule.authUser(request.getUser())) {
            throw new AuthException(ServerBundle.getString("exception.no_auth"));
        }
        Log.getLogger().info(ServerBundle.getString("server.ask_route_requirement"));
        if (commandInvoker.checkRouteRequirement(request.getUserString())) {
            Log.getLogger().info(ServerBundle.getString("server.ask_route_positive"));
            return responseCreator.createResponse("", true, true);
        } else {
            Log.getLogger().info(ServerBundle.getString("server.ask_route_negative"));
            return responseCreator.createResponse("", true, false);
        }
    }

    private Response handleCommandRequest(Request request) throws CommandExecutionException, CommandNotFoundException, AuthException {
        if (request.getUser() == null || !userAuthModule.authUser(request.getUser())) {
            throw new AuthException(ServerBundle.getString("exception.no_auth"));
        }
        Log.getLogger().info(ServerBundle.getFormattedString("server.execute_attempt", request.getUserString()));
        commandInvoker.execute(request.getUserString(), request.getRoute());
        return responseCreator.createResponse();
    }
}
