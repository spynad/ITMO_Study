package server;

import collection.RouteStackManager;
import command.CommandInvoker;
import connection.ConnectionListener;
import connection.ConnectionOpener;
import exception.CommandNotFoundException;
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

    public static void setIsRunning(boolean b) {
        isRunning = b;
    }

    public void start() {
        Selector selector;
        Creator creator = new ResponseCreator();
        main.RouteCollectionManager routeManager = new RouteStackManager(creator);
        CommandInvoker commandInvoker = new CommandInvoker(routeManager, new BufferedReader(new InputStreamReader(System.in)));
        ConnectionOpener connectionOpener = new ConnectionOpener();
        ConnectionListener connectionListener = new ConnectionListener();
        RequestReader requestReader = new RequestReader();
        RequestHandler requestHandler = new RequestHandler(commandInvoker, creator);
        ResponseSender responseSender = new ResponseSender();
        /*try {
            connectionListener.setSelector(connectionOpener.getConnection("localhost", 2020));
            connectionListener.setChannel(connectionOpener.getCurrentChannel());
        } catch (IOException ioe) {
            Log.getLogger().error(ioe);
        }*/
        while (isRunning) {
            try {
                connectionListener.setSelector(connectionOpener.getConnection("localhost", 2020));
                connectionListener.setChannel(connectionOpener.getCurrentChannel());
                selector = connectionListener.listen();
                Request request = requestReader.getRequest(selector);
                try {
                    Response response = requestHandler.handleRequest(request);
                    responseSender.sendResponse(selector, response);
                    connectionListener.stop();
                } catch (CommandNotFoundException e) {
                    Log.getLogger().error(e);
                    Response response = new Response(e.getMessage(), false, false);
                    responseSender.sendResponse(selector, response);
                    connectionListener.stop();
                }
                Log.getLogger().info(request.toString());
            } catch (IOException | ClassNotFoundException ioe) {
                Log.getLogger().error(ioe);
            }
        }
    }
}
