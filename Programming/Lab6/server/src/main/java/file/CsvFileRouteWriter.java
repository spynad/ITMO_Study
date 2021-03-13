package file;

import java.io.*;
import java.util.Stack;
import java.util.logging.Level;

import collection.RouteCollectionManager;
import log.Log;
import route.Route;

/**
 * Класс, отвечающий за взаимодействие с файлами
 * @author spynad
 */
public class CsvFileRouteWriter implements RouteWriter {
    private final RouteCollectionManager routeManager;

    private final String fileName;

    public CsvFileRouteWriter(RouteCollectionManager routeManager, String fileName) {
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
            String output = makeFileFromRoute(routeManager.getRoutes());
            printWriter.print(output);

        } catch(IOException ioe) {
            System.err.println("Exception while trying to write to a file");
            System.exit(1);
        }
    }


    private String makeFileFromRoute(Stack<Route> routes) {
        StringBuilder csv = new StringBuilder();
        csv.append("id,name,coordinates,creationDate,from,to,distance\n");
        for (Route route: routes) {
            csv.append(route.toString()).append('\n');
        }
        return csv.toString();
    }
}
