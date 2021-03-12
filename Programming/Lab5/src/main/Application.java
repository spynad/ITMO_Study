package main;

import command.CommandInvoker;
import exception.InvalidArgumentException;
import exception.RouteBuildException;
import exception.RouteReadException;
import io.ConsoleIO;
import io.UserIO;
import log.Log;

import java.io.IOException;
import java.util.logging.Level;

public class Application {
    CommandInvoker commandInvoker;
    UserIO userIO;
    private static boolean isRunning = true;
    private static String fileName;

    public Application() {}

    public static void setIsRunning(boolean isRunning) {
        Application.isRunning = isRunning;
    }

    public static String getFileName() {
        return fileName;
    }

    public void start(String fileName) {
        Application.fileName = fileName;
        userIO = new ConsoleIO();
        RouteCollectionManager routeManager = new RouteStackManager(userIO);
        RouteWriter routeWriter = new CsvFileRouteWriter(routeManager, fileName);
        RouteReader routeReader = new CsvFileRouteReader(routeManager, fileName);
        commandInvoker = new CommandInvoker(routeManager, userIO, routeWriter);
        commandInvoker.putCommands();
        try {
            routeManager.addRoutes(routeReader.read());
        } catch (InvalidArgumentException | RouteReadException | RouteBuildException iae) {
            System.err.println("application init error: " + iae.getMessage());
        }
        loop();
    }

    private void loop() {
        while(isRunning) {
            try {
                userIO.printUserPrompt();
                String str = userIO.readLine();
                commandInvoker.execute(str);
            } catch (IllegalStateException | IOException ise) {
                userIO.printErrorMessage(ise.getMessage());
                Log.logger.log(Level.WARNING, "EXCEPTION: ", ise);
            }
        }
    }
}
