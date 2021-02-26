package main;

import command.CommandInvoker;
import log.Log;
import managers.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class Application {
    CommandInvoker commandInvoker;
    private static BufferedReader input;
    private static boolean isRunning = true;

    public Application() {}

    public static void setIsRunning(boolean isRunning) {
        Application.isRunning = isRunning;
    }

    public static void setInput(BufferedReader input) {
        Application.input = input;
    }

    public static BufferedReader getInput() {
        return input;
    }

    public void init(String fileName) {
        CollectionRouteManager routeManager = new StackRouteManager();
        IOManager fileManager = new FileManager(routeManager, fileName);
        commandInvoker = new CommandInvoker(routeManager, fileManager);
        input = new BufferedReader(new InputStreamReader(System.in));

        routeManager.addRoutes(fileManager.read());
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
