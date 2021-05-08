package client;

import commands.*;
import connection.ConnectionManager;
import connection.ConnectionManagerImpl;
import exception.*;
import io.ConsoleIO;
import io.ConsoleRouteParser;
import io.SingleRouteReader;
import io.UserIO;
import locale.ClientLocale;
import request.RequestCreator;
import request.RequestSender;
import request.RequestSenderImpl;
import response.ResponseReader;
import response.ResponseReaderImpl;
import route.Request;
import route.RequestType;
import route.Response;

import java.io.EOFException;
import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.Locale;

public class Application {
    private final String address;
    private final int port;
    private final UserIO userIO;
    private final CommandInvoker commandInvoker;
    private final ConnectionManager connectionManager;
    private final RequestCreator requestCreator;
    private final RequestSender requestSender;
    private final ResponseReader reader;
    private final SingleRouteReader routeReader;
    private boolean isRunning = true;


    public Application(String address, int port) {
        Locale.setDefault(new Locale("ru", "RU"));
        this.address = address;
        this.port = port;
        userIO = new ConsoleIO();
        commandInvoker = new CommandInvoker();
        connectionManager = new ConnectionManagerImpl();
        requestCreator = new RequestCreator();
        requestSender = new RequestSenderImpl();
        reader = new ResponseReaderImpl();
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
            } catch (BadCSVException | CommandExecutionException executionException) {
                userIO.printErrorMessage(ClientLocale.getString("exception.command_exec_error") + ": " + executionException.getMessage());
            } catch (IOException ioe) {
                userIO.printErrorMessage(ioe.getMessage());
            } catch (CommandNotFoundException ise) {
                try {
                    Response response = communicateWithServer(userString, routeReader);
                    userIO.printLine(response.getMessage());
                } catch (EOFException eofe) {
                    userIO.printErrorMessage(ClientLocale.getString("exception.too_many_bytes"));
                } catch (IOException | ClassNotFoundException ioe) {
                    userIO.printErrorMessage(ClientLocale.getString("exception.general_network"));
                    ioe.printStackTrace();
                } catch (RouteReadException | RouteBuildException | IllegalStateException e) {
                    userIO.printErrorMessage(e.getMessage());
                    try {
                        connectionManager.closeConnection();
                    } catch (IOException ioe) {
                        return;
                    }
                }
            }
        }
    }

    public Response communicateWithServer(String userString, SingleRouteReader routeReader) throws IOException, ClassNotFoundException,
            RouteReadException, RouteBuildException {
        SocketChannel socketChannel = connectionManager.openConnection(address, port);
        Request request = requestCreator.createRouteRequest(userString);
        requestSender.initOutputStream(socketChannel);
        requestSender.sendRequest(socketChannel, request);
        Response response = reader.getResponse(socketChannel);

        if (!response.isSuccess())
            throw new IllegalStateException(response.getMessage());

        connectionManager.closeConnection();
        socketChannel = connectionManager.openConnection(address, port);

        if (response.isRouteRequired()) {
            request.setRoute(routeReader.read());
        }

        request.setType(RequestType.COMMAND_REQUEST);
        requestSender.initOutputStream(socketChannel);
        requestSender.sendRequest(socketChannel, request);
        response = reader.getResponse(socketChannel);

        if (!response.isSuccess())
            throw new IllegalStateException(response.getMessage());

        connectionManager.closeConnection();

        return response;
    }

    private void setCommands(CommandInvoker commandInvoker) {
        commandInvoker.addCommand("exit", new ExitCommand(this));
        commandInvoker.addCommand("execute_script", new ExecuteScriptCommand(this, commandInvoker, userIO));
        commandInvoker.addCommand("client_help", new ClientHelpCommand(userIO));
    }
}
