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
import transferobjects.Request;
import transferobjects.RequestType;
import transferobjects.Response;
import route.Route;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
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
    private final AuthModule authModule;
    private final ValidatorFactory validatorFactory;
    private boolean isRunning = true;


    public Application(String address, int port) {
        Locale.setDefault(new Locale("ru"));
        this.address = address;
        this.port = port;
        userIO = new ConsoleIO();
        commandInvoker = new CommandInvoker();
        connectionManager = new ConnectionManagerImpl();
        requestSender = new RequestSenderImpl();
        reader = new ResponseReaderImpl();
        authModule = new AuthModule(userIO, connectionManager, requestSender, reader);
        validatorFactory = Validation.buildDefaultValidatorFactory();
        routeReader = new ConsoleRouteParser(userIO, authModule, validatorFactory);
        requestCreator = new RequestCreator(authModule);
        setCommands(commandInvoker);
    }

    public void setIsRunning(boolean b) {
        isRunning = b;
    }


    public void start() {
        try {
            connectionManager.openConnection(address, port);
        } catch (IOException e) {
            userIO.printErrorMessage(ClientLocale.getString("client.server_unreachable"));
            return;
        }
        while(isRunning) {
            String userString = "";
            try {
                userIO.printUserPrompt();
                userString = userIO.readLine();
                commandInvoker.execute(userString, null);
            } catch (BadCSVException | CommandExecutionException executionException) {
                userIO.printErrorMessage(ClientLocale.getString("exception.command_exec_error") + ": " + executionException.getMessage());
            } catch (IOException ioe) {
                userIO.printErrorMessage(ioe.getMessage());
                isRunning = false;
            } catch (CommandNotFoundException ise) {
                try {
                    Response response = communicateWithServer(userString, routeReader);
                    userIO.printLine(response.getMessage());
                } catch (EOFException eofe) {
                    userIO.printErrorMessage(ClientLocale.getString("exception.too_many_bytes"));
                } catch (IOException | ClassNotFoundException ioe) {
                    userIO.printErrorMessage(ClientLocale.getString("exception.general_network_reconnect"));
                    try {
                        connectionManager.openConnection(address, port);
                        userIO.printErrorMessage(ClientLocale.getString("client.successful_reconnect_attempt"));
                        /*Response response = communicateWithServer(userString, routeReader);
                        userIO.printLine(response.getMessage());*/
                    } catch (IOException e) {
                        userIO.printErrorMessage(ClientLocale.getString("client.failed_reconnect_attempt"));
                    }
                } catch (IllegalStateException e) {
                    userIO.printErrorMessage(e.getMessage());
                }
            }
        }
    }

    public Response communicateWithServer(String userString, SingleRouteReader routeReader) throws IOException, ClassNotFoundException {
        SocketChannel socketChannel = connectionManager.getConnection();
        Request request = requestCreator.createRouteRequest(userString);
        requestSender.sendRequest(socketChannel, request);
        Response response = reader.getResponse(socketChannel);

        if (!response.isSuccess())
            throw new IllegalStateException(response.getMessage());


        if (response.isRouteRequired()) {
            Route route;
            try {
                route = routeReader.read();
            } catch (RouteBuildException | RouteReadException e) {
                throw new IllegalStateException(e.getMessage());
            }
            request = requestCreator.createCommandRequest(userString, route);
        }

        request.setType(RequestType.COMMAND_REQUEST);
        requestSender.sendRequest(socketChannel, request);
        response = reader.getResponse(socketChannel);

        if (!response.isSuccess())
            throw new IllegalStateException(response.getMessage());
        return response;
    }

    private void setCommands(CommandInvoker commandInvoker) {
        commandInvoker.addCommand("exit", new ExitCommand(this));
        commandInvoker.addCommand("execute_script", new ExecuteScriptCommand(this, commandInvoker, userIO, validatorFactory));
        commandInvoker.addCommand("client_help", new ClientHelpCommand(userIO));
        commandInvoker.addCommand("auth", new AuthCommand(authModule));
        commandInvoker.addCommand("register", new RegisterCommand(authModule));
        commandInvoker.addCommand("change_language", new ChangeLanguageCommand(userIO));
    }
}
