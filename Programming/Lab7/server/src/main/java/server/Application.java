package server;

import collection.*;
import command.*;
import connection.*;
import exception.*;
import request.*;
import response.*;
import io.*;
import data.DAOFactory;
import data.RouteDAO;
import log.Log;

import java.io.IOException;
import java.util.Locale;

public class Application {
    private volatile boolean isRunning = true;

    public void start(String address, int port) {
        Locale.setDefault(new Locale("ru"));
        Creator creator = new ResponseCreator();
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        RouteDAO routeDAO = daoFactory.getRouteDAO();
        UserAuthModule userAuthModule = new UserAuthModule(daoFactory.getUserDAO());
        RouteCollectionManager routeManager = new RouteStackManager(creator, routeDAO, userAuthModule);
        CommandHistory commandHistory = new CommandHistory();
        CommandInvoker commandInvoker = new CommandInvoker(commandHistory);

        try {
            routeManager.addRoutesFromDB();
        } catch(PersistentException e) {
            System.out.println(e.getDbErrorMessage());
        }
        ConnectionListener connectionListener = new ConnectionListenerImpl();
        RequestReader requestReader = new RequestReaderImpl();
        RequestHandler requestHandler = new RequestHandlerImpl(commandInvoker, creator, userAuthModule);
        ResponseSender responseSender = new ResponseSenderImpl();
        putCommands(commandInvoker, routeManager, creator, commandHistory);
        Server server = new Server(connectionListener, requestReader, requestHandler, responseSender);
        putServerCommands(commandInvoker, connectionListener, server);
        consoleStart(commandInvoker);
        try {
            connectionListener.openConnection(address, port);
            while (isRunning) {
                server.start();
            }
        } catch (IOException e) {
            Log.getLogger().error(e);
            Log.getLogger().error(e.getStackTrace());
        }
    }

    private void consoleStart(CommandInvoker commandInvoker) {
        Thread consoleThread = new Thread(() -> {
            UserIO userIO = new ConsoleIO();
            //while(!Thread.interrupted()) {
                userIO.printUserPrompt();
                while(isRunning) {
                    try {
                        String str = userIO.readLine();
                        commandInvoker.execute(str);
                    } catch (IOException | CommandNotFoundException | CommandExecutionException ioe) {
                        userIO.printErrorMessage(ioe.getMessage());
                    }
                }
            //}
        });
        consoleThread.setDaemon(true);
        consoleThread.start();
    }

    private void putCommands(CommandInvoker commandInvoker, RouteCollectionManager manager, Creator creator, CommandHistory commandHistory) {
        commandInvoker.addCommand("help", new HelpCommand(false, creator));
        commandInvoker.addCommand("info", new InfoCommand(manager, false));
        commandInvoker.addCommand("clear", new ClearCommand(manager, false));
        commandInvoker.addCommand("remove_all_by_distance", new RemoveAllByDistanceCommand(manager, false));
        commandInvoker.addCommand("sum_of_distance", new SumOfDistanceCommand(manager, false));
        commandInvoker.addCommand("show", new ShowCommand(manager, false));
        commandInvoker.addCommand("remove_first", new RemoveFirstCommand(manager, false));
        commandInvoker.addCommand("remove_by_id", new RemoveByIdCommand(manager, false));
        commandInvoker.addCommand("remove_at", new RemoveAtCommand(manager, false));
        commandInvoker.addCommand("filter_contains_name", new FilterContainsNameCommand(manager, false));
        commandInvoker.addCommand("history", new HistoryCommand(commandHistory, creator, false));
        commandInvoker.addCommand("add", new AddCommand(manager, true));
        commandInvoker.addCommand("update", new UpdateCommand(manager, true));
    }

    private void putServerCommands(CommandInvoker commandInvoker, ConnectionListener connectionListener, Server server) {
        commandInvoker.addServerCommand("exit", new ExitCommand(connectionListener, this, server));
    }

    public synchronized void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
}
