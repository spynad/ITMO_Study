package client;

import commands.*;
import connection.ConnectionOpener;
import exception.CommandExecutionException;
import exception.CommandNotFoundException;
import exception.RouteBuildException;
import exception.RouteReadException;
import io.ConsoleIO;
import io.ConsoleRouteParser;
import io.SingleRouteReader;
import io.UserIO;
import request.RequestCreator;
import request.RequestSender;
import response.ResponseReader;
import route.Request;
import route.RequestType;
import route.Response;

import java.io.EOFException;
import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

public class Application {
    private String address;
    private int port;
    private UserIO userIO;
    private CommandInvoker commandInvoker;
    private ConnectionOpener connectionOpener;
    private RequestCreator requestCreator;
    private RequestSender requestSender;
    private ResponseReader reader;
    private SingleRouteReader routeReader;
    private boolean isRunning = true;

    public Application(String address, int port) {
        this.address = address;
        this.port = port;
        userIO = new ConsoleIO();
        commandInvoker = new CommandInvoker(this);
        connectionOpener = new ConnectionOpener();
        requestCreator = new RequestCreator();
        requestSender = new RequestSender();
        reader = new ResponseReader();
        routeReader = new ConsoleRouteParser(userIO);
        setCommands(commandInvoker);
    }

    public void setIsRunning(boolean b) {
        isRunning = b;
    }

    public void start() {
        while(isRunning) {
            userIO.printUserPrompt();
            String userString = "";
            try {
                userString = userIO.readLine();
                commandInvoker.execute(userString, null);
            } catch (CommandExecutionException executionException) {
                userIO.printErrorMessage("Command execution error: " + executionException.getMessage());
            } catch (IOException ioe) {
                userIO.printErrorMessage("IO error:" + ioe.getMessage());
            } catch (CommandNotFoundException ise) {
                try {
                    SocketChannel socketChannel = connectionOpener.openConnection(address, port);
                    Request request = requestCreator.createRouteRequest(userString);
                    requestSender.initOutputStream(socketChannel);
                    requestSender.sendRequest(socketChannel, request);
                    Response response = reader.getResponse(socketChannel);

                    if (!response.isSuccess())
                        throw new IllegalStateException(response.getMessage());

                    connectionOpener.closeConnection();
                    socketChannel = connectionOpener.openConnection(address, port);

                    if (response.isRouteRequired()) {
                        request.setRoute(routeReader.read());
                    }

                    request.setType(RequestType.COMMAND_REQUEST);
                    requestSender.initOutputStream(socketChannel);
                    requestSender.sendRequest(socketChannel, request);
                    response = reader.getResponse(socketChannel);

                    if (!response.isSuccess())
                        throw new IllegalStateException(response.getMessage());

                    connectionOpener.closeConnection();
                    userIO.printLine(response.getMessage());
                } catch (EOFException eofe) {
                    userIO.printErrorMessage("network error: got too many bytes from the server");
                } catch (IOException | ClassNotFoundException ioe) {
                    userIO.printErrorMessage("network error:" + ioe.getMessage());
                } catch (RouteReadException | RouteBuildException | IllegalStateException e) {
                    userIO.printErrorMessage(e.getMessage());
                }
            }
        }
    }

    private void setCommands(CommandInvoker commandInvoker) {
        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("exit", new ExitCommand(this));
        commandMap.put("execute_script", new ExecuteScriptCommand(commandInvoker));
        commandMap.put("help", new HelpCommand());
        commandInvoker.setCommands(commandMap);
    }
}
