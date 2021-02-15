import csv.CSVParser;
import csv.Parser;
import managers.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Lab5 {

    public static void main(String[] args) {
        if (args.length > 0) {
            if(!args[0].equals("")) {
                IRouteManager routeManager = new RouteManager();
                Parser csvParser = new CSVParser();
                IFileManager fileManager = new FileManager(routeManager);

                routeManager.addRoutes(csvParser.parseRouteFromFile(fileManager.readFile(args[0])));
                fileManager.writeFile();

                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                ClientManager clientManager = new ClientManager(routeManager, fileManager, input);
                clientManager.start();
            }
        }
        else {
            System.err.println("Usage: program_name fileName");
        }
    }
}
