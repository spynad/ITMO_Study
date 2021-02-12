package csv;

import csv.exceptions.BadCSVException;
import log.Log;
import route.Coordinates;
import route.FirstLocation;
import route.Route;
import route.SecondLocation;
import route.exceptions.InvalidArgumentException;
import csv.exceptions.BadCSVException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;

public class CSVReader {

    public CSVReader() {
        Log.logger.log(Level.INFO,"CSVReader init");
    }
    //TODO: это пиздец, переделать это дерьмо
    public ArrayList<Route> makeRouteFromCSV(ArrayList<String> inputString) {
        ArrayList<Route> routes = new ArrayList<>();
        Log.logger.log(Level.INFO,"Parsing file");
        try {
            if(!inputString.get(0).equals("name,coordinates,creationDate,from,to,distance"))
                throw new BadCSVException();
            inputString.remove(0);
            for (String str : inputString) {
                String formattedStr = str.replace("\"", "");
                String[] params = formattedStr.split(",");
                if(params.length != 10)
                    throw new BadCSVException();
                System.out.println(Arrays.toString(params));
                String name = params[0];
                Coordinates coordinates = new Coordinates(Long.parseLong(params[1]),
                        Double.parseDouble(params[2]));
                FirstLocation floc = new FirstLocation(Integer.parseInt(params[3]),
                        Long.parseLong(params[4]), params[5]);
                SecondLocation sloc = new SecondLocation(Integer.parseInt(params[6]),
                        Long.parseLong(params[7]), Double.parseDouble(params[8]));
                double dist = Double.parseDouble(params[9]);
                Route route = new Route(name, coordinates, floc, sloc, dist);
                routes.add(route);
            }
        } catch(NumberFormatException | BadCSVException nfe) {
            System.err.println("Exception while trying to read CSV file");
            System.exit(1);
        } catch(Exception | InvalidArgumentException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return routes;
    }
}
