package main;

import java.io.*;
import java.util.Stack;
import java.util.logging.Level;

import log.Log;
import route.Route;

/**
 * Класс, отвечающий за взаимодействие с файлами
 * @author spynad
 */
public class CsvFileRouteWriter implements RouteWriter {
    private final CollectionRouteManager routeManager;

    private final String fileName;

    public CsvFileRouteWriter(CollectionRouteManager routeManager, String fileName) {
        Log.logger.log(Level.INFO,"FileManager init");
        this.routeManager = routeManager;
        this.fileName = fileName;
    }

    /**
     * Метдо, записывающий csv-строчку в файл
     */
    public void write() {
        try (
                PrintWriter printWriter = new PrintWriter(new FileWriter(fileName))
        ) {
            Log.logger.log(Level.INFO,"Writing csv string to csv file");
            String output = makeFileFromRoute(routeManager.getRoutes());
            printWriter.print(output);

        } catch(IOException ioe) {
            System.err.println("Exception while trying to write to a file");
            Log.logger.log(Level.WARNING, "EXCEPTION: ", ioe);
            System.exit(1);
        }
    }


    private String makeFileFromRoute(Stack<Route> routes) {
        Log.logger.log(Level.INFO,"Making csv string");
        StringBuilder csv = new StringBuilder();
        csv.append("id,name,coordinates,creationDate,from,to,distance\n");
        for (Route route: routes) {
            csv.append(route.toString()).append('\n');
        }
        return csv.toString();
    }
}
