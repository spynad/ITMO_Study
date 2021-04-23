package server;

import connection.ConnectionListener;
import exception.AuthException;
import exception.CommandExecutionException;
import exception.CommandNotFoundException;
import exception.PersistentException;
import log.Log;
import request.RequestHandler;
import request.RequestReader;
import response.ResponseSender;
import transferobjects.Request;
import transferobjects.Response;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class Server {
    private final ExecutorService readRequests = Executors.newFixedThreadPool(10);
    private final ExecutorService handleRequests = new ForkJoinPool(10);
    private final ExecutorService sendResponses = Executors.newCachedThreadPool();
    private final ConnectionListener connectionListener;
    private final RequestReader requestReader;
    private final RequestHandler requestHandler;
    private final ResponseSender responseSender;

    public Server(ConnectionListener listener, RequestReader reader, RequestHandler handler, ResponseSender sender) {
        connectionListener = listener;
        requestReader = reader;
        requestHandler = handler;
        responseSender = sender;
    }

    public void start() throws IOException {
        SocketChannel socket = connectionListener.listen();
        Runnable readingRequestRunnable = () -> {
            try {
                while (true) {
                    Request request = requestReader.getRequest(socket);
                    handleRequest(request, socket);
                }
            } catch (IOException | ClassNotFoundException e) {
                Log.getLogger().error(e);
                Log.getLogger().error(e.getStackTrace());
            }
        };
        readRequests.submit(readingRequestRunnable);
    }

    private void handleRequest(Request request, SocketChannel socket) {
        Runnable handlingRequestRunnable = () -> {
            try {
                Response response = requestHandler.handleRequest(request);
                sendResponse(response, socket);
            } catch (CommandNotFoundException | CommandExecutionException | AuthException e) {
                Log.getLogger().error(e);
                Log.getLogger().error(e.getStackTrace());
                Response response = new Response(e.getMessage(), false, false);
                sendResponse(response, socket);
            } catch (PersistentException e) {
                Log.getLogger().error(e.getDbErrorMessage());
                Log.getLogger().error(e);
                Response response = new Response(e.getMessage(), false, false);
                sendResponse(response, socket);
            }
        };
        handleRequests.submit(handlingRequestRunnable);
    }

    private void sendResponse(Response response, SocketChannel socket) {
        Runnable sendingResponseRunnable = () -> {
            try {
                responseSender.sendResponse(socket, response);
            } catch (IOException | ClassNotFoundException e) {
                Log.getLogger().error(e);
                Log.getLogger().error(e.getStackTrace());
            }
        };
        sendResponses.submit(sendingResponseRunnable);
    }

    public void shutdownExecutorServices() {
        readRequests.shutdownNow();
        handleRequests.shutdown();
        sendResponses.shutdown();
    }

}
