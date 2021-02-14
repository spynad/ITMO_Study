import csv.CSVParser;
import csv.Parser;
import managers.*;

public class Lab5 {

    public static void main(String[] args) {
        if (args.length > 0) {
            if(!args[0].equals("")) {
                IRouteManager routeManager = new RouteManager();
                Parser csvParser = new CSVParser();
                IFileManager fileManager = new FileManager(routeManager, csvParser);

                routeManager.addRoutes(csvParser.parseRouteFromFile(fileManager.readFile(args[0])));
                fileManager.writeFile();

                ClientManager clientManager = new ClientManager(routeManager);
                clientManager.start();
            }
        }
        else {
            System.out.println("Usage: program_name fileName");
        }
    }
}
