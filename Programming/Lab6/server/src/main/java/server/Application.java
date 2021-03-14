package server;

import collection.RouteCollectionManager;
import collection.RouteStackManager;
import command.*;
import connection.ConnectionListener;
import connection.ConnectionOpener;
import exception.*;
import file.CsvFileRouteReader;
import file.CsvFileRouteWriter;
import file.RouteReader;
import file.RouteWriter;
import log.Log;
import request.RequestHandler;
import request.RequestReader;
import response.Creator;
import response.ResponseCreator;
import response.ResponseSender;
import route.Request;
import route.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.Selector;

public class Application {
    private static boolean isRunning = true;
    private String fileNameArg;

    public static void setIsRunning(boolean b) {
        isRunning = b;
    }

    public void start(int port, String fileName) {
        fileNameArg = fileName;
        Selector selector;
        Creator creator = new ResponseCreator();
        RouteCollectionManager routeManager = new RouteStackManager(creator);
        RouteReader reader = new CsvFileRouteReader(routeManager, fileName);
        RouteWriter writer = new CsvFileRouteWriter(routeManager, fileName);
        CommandInvoker commandInvoker = new CommandInvoker(routeManager, writer, creator);
        putCommands(commandInvoker, routeManager, creator);

        try {
            reader.read();
        } catch (InvalidArgumentException | RouteReadException | RouteBuildException e) {
            log.Log.getLogger().error(e.getMessage());
        }
        ConnectionOpener connectionOpener = new ConnectionOpener();
        ConnectionListener connectionListener = new ConnectionListener();
        RequestReader requestReader = new RequestReader();
        RequestHandler requestHandler = new RequestHandler(commandInvoker, creator);
        ResponseSender responseSender = new ResponseSender();

        while (isRunning) {
            try {
                connectionListener.setSelector(connectionOpener.getConnection("localhost", port));
                connectionListener.setChannel(connectionOpener.getCurrentChannel());
                selector = connectionListener.listen();
                Request request = requestReader.getRequest(selector);
                log.Log.getLogger().info("Got request: " + request.toString());
                try {
                    log.Log.getLogger().info("Handling request: " + request.toString());
                    Response response = requestHandler.handleRequest(request);
                    responseSender.sendResponse(selector, response);
                    connectionListener.stop();
                } catch (CommandNotFoundException | CommandExecutionException e) {
                    Log.getLogger().error(e);
                    Response response = new Response(e.getMessage(), false, false);
                    responseSender.sendResponse(selector, response);
                    connectionListener.stop();
                }
                Log.getLogger().info(request.toString());
            } catch (IOException | ClassNotFoundException ioe) {
                Log.getLogger().error(ioe);
                Log.getLogger().error(ioe.getMessage());
                try {
                    connectionOpener.closeConnection();
                } catch (IOException e) {
                    Log.getLogger().error(e);
                    System.exit(1);
                }
                //System.exit(1);
            }
        }
    }

    private void putCommands(CommandInvoker commandInvoker, RouteCollectionManager manager, Creator creator) {
        commandInvoker.addCommand("help", new HelpCommand(false));
        commandInvoker.addCommand("exit", new ExitCommand(false));
        commandInvoker.addCommand("info", new InfoCommand(manager, false));
        commandInvoker.addCommand("clear", new ClearCommand(manager, false));
        commandInvoker.addCommand("remove_all_by_distance", new RemoveAllByDistanceCommand(manager, false));
        commandInvoker.addCommand("sum_of_distance", new SumOfDistanceCommand(manager, false));
        commandInvoker.addCommand("show", new ShowCommand(manager, false));
        commandInvoker.addCommand("remove_first", new RemoveFirstCommand(manager, false));
        commandInvoker.addCommand("remove_by_id", new RemoveByIdCommand(manager, false));
        commandInvoker.addCommand("remove_at", new RemoveAtCommand(manager, false));
        commandInvoker.addCommand("filter_contains_name", new FilterContainsNameCommand(manager, false));
        commandInvoker.addCommand("history", new HistoryCommand(commandInvoker, creator, false));
        commandInvoker.addCommand("add", new AddCommand(manager, true));
        commandInvoker.addCommand("update", new UpdateCommand(manager, true));
    }
}
