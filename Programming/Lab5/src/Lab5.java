import csv.CSVReader;
import log.Log;
import managers.FileManager;
import managers.RouteManager;

import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Lab5 {

    public static void main(String[] args) {
        if (args.length > 0) {
            if(!args[0].equals("")) {
                RouteManager routeManager = new RouteManager();
                FileManager fileManager = new FileManager();
                CSVReader csvReader = new CSVReader();
                routeManager.addRoutes(csvReader.makeRouteFromCSV(fileManager.readFile(args[0])));
                fileManager.writeFile(routeManager);
            }
        }
        else {
            System.out.println("Usage: program_name fileName");
        }
    }
}
