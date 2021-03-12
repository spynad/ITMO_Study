package client;

import commands.*;
import connection.ConnectionOpener;
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

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

public class Application {
    private static boolean isRunning = true;

    public static void setIsRunning(boolean b) {
        isRunning = b;
    }

    public void start(String address, int port) {
        UserIO userIO = new ConsoleIO();
        CommandInvoker commandInvoker = new CommandInvoker();
        ConnectionOpener connectionOpener = new ConnectionOpener();
        RequestCreator requestCreator = new RequestCreator();
        RequestSender requestSender = new RequestSender();
        ResponseReader reader = new ResponseReader();
        SingleRouteReader routeReader = new ConsoleRouteParser(userIO);
        setCommands(commandInvoker);

        while(isRunning) {
            userIO.printUserPrompt();
            String userString = "";
            try {
                userString = userIO.readLine();
                commandInvoker.execute(userString, null);
            } catch (IOException ioe) {
                userIO.printErrorMessage("IO error:" + ioe.getMessage());
            } catch (CommandNotFoundException ise) {
                try {
                    SocketChannel socketChannel = connectionOpener.openConnection(address, port);
                    Request request = requestCreator.createRouteRequest(userString);
                    requestSender.sendRequest(socketChannel, request);
                    Response response = reader.getResponse(socketChannel);
                    if (!response.isSuccess())
                        throw new IllegalStateException(response.getMessage());
                    connectionOpener.closeConnection();
                    socketChannel = connectionOpener.openConnection(address, port);
                    if (!response.isRouteRequired()) {
                        request.setType(RequestType.COMMAND_REQUEST);
                        requestSender.sendRequest(socketChannel, request);

                    } else {
                        request.setRoute(routeReader.read());
                        request.setType(RequestType.COMMAND_REQUEST);
                        requestSender.sendRequest(socketChannel, request);
                    }
                    response = reader.getResponse(socketChannel);
                    if (!response.isSuccess())
                        throw new IllegalStateException(response.getMessage());
                    connectionOpener.closeConnection();
                    userIO.printLine(response.getMessage());
                } catch (IOException | ClassNotFoundException ioe) {
                    userIO.printErrorMessage("network error:" + ioe.getMessage());
                } catch (RouteReadException | RouteBuildException e) {
                    e.printStackTrace();
                } catch (IllegalStateException illegalStateException) {
                    userIO.printErrorMessage(illegalStateException.getMessage());
                }
            }
        }
    }

    private void setCommands(CommandInvoker commandInvoker) {
        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("exit", new ExitCommand());
        commandMap.put("execute_script", new ExecuteScriptCommand());
        commandMap.put("help", new HelpCommand());
        commandInvoker.setCommands(commandMap);
    }
}
