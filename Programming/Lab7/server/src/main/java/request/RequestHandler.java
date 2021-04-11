package request;

import exception.CommandExecutionException;
import exception.CommandNotFoundException;
import route.Request;
import route.Response;

public interface RequestHandler {
    Response handleRequest(Request request) throws CommandNotFoundException, CommandExecutionException;
}
