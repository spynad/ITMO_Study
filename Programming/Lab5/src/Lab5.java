import csv.CSVRouteParser;
import csv.Parser;
import managers.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Lab5 {

    public static void main(String[] args) {
        //TODO: если ввести пустую строчку в аргументе, например, "", тогда программа ничего не выведет, исправить
        if (args.length > 0) {
            if(!args[0].equals("")) {
                IRouteManager routeManager = new RouteManager();
                Parser csvParser = new CSVRouteParser(routeManager);
                IFileManager fileManager = new FileManager(routeManager, csvParser);

                fileManager.setFileName(args[0]);
                routeManager.addRoutes(csvParser.parseRouteFromFile(fileManager.readFile()));
                fileManager.writeFile();

                IClientManager clientManager = new ClientManager(routeManager, fileManager);
                clientManager.start();
            }
        }
        else {
            System.err.println("Usage: program_name fileName");
        }
    }
}
