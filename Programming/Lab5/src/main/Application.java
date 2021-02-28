package main;

import command.CommandInvoker;
import exception.InvalidArgumentException;
import exception.RouteBuildException;
import exception.RouteReadException;
import log.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class Application {
    CommandInvoker commandInvoker;
    private static BufferedReader input;
    private static boolean isRunning = true;
    private static String fileName;

    public Application() {}

    public static void setIsRunning(boolean isRunning) {
        Application.isRunning = isRunning;
    }

    public static BufferedReader getInput() {
        return input;
    }

    public static String getFileName() {
        return fileName;
    }

    public void init(String fileName) {
        Application.fileName = fileName;
        CollectionRouteManager routeManager = new StackRouteManager();
        RouteReader routeReader = new CsvFileRouteReader(routeManager, fileName);
        commandInvoker = new CommandInvoker(routeManager);
        input = new BufferedReader(new InputStreamReader(System.in));
        try {
            routeManager.addRoutes(routeReader.read());
        } catch (InvalidArgumentException | RouteReadException | RouteBuildException iae) {
            System.err.println("application init error: " + iae.getMessage());
        }
    }

    public void loop() {
        while(isRunning) {
            try {
                System.out.print("> ");
                String str = input.readLine();
                commandInvoker.execute(str);
            } catch (IllegalStateException | IOException ise) {
                System.err.println(ise.getMessage());
                Log.logger.log(Level.WARNING, "EXCEPTION: ", ise);
            }

        }
    }
}
