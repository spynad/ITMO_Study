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
import java.util.Iterator;
import java.util.Stack;
import java.util.logging.Level;

public class CSVParser implements Parser{

    public CSVParser() {
        Log.logger.log(Level.INFO,"CSVReader object created");
    }
    //TODO: это пиздец, переделать это дерьмо
    public ArrayList<Route> parseRouteFromFile(ArrayList<String> inputString) {
        ArrayList<Route> routes = new ArrayList<>();
        Log.logger.log(Level.INFO,"Parsing file");
        try {
            if(!inputString.get(0).equals("name,coordinates,creationDate,from,to,distance"))
                throw new BadCSVException();
            inputString.remove(0);
            for (String str : inputString) {
                String formattedStr = str.replace("\"", "");
                String[] params = formattedStr.split(",");
                if(params.length != 13)
                    throw new BadCSVException();
                String name = params[0];
                Coordinates coordinates = new Coordinates(Long.parseLong(params[1]),
                        Double.parseDouble(params[2]));
                LocalDate date = LocalDate.of(Integer.parseInt(params[3]),
                        Integer.parseInt(params[4]),
                        Integer.parseInt(params[5]));
                FirstLocation floc = new FirstLocation(Integer.parseInt(params[6]),
                        Long.parseLong(params[7]),
                        params[8]);
                SecondLocation sloc = new SecondLocation(Integer.parseInt(params[9]),
                        Long.parseLong(params[10]),
                        Double.parseDouble(params[11]));
                double dist = Double.parseDouble(params[12]);
                Route route = new Route(name, coordinates, date, floc, sloc, dist);
                routes.add(route);
            }
        } catch(NumberFormatException | BadCSVException nfe) {
            System.err.println("Exception while trying to read CSV file: " + nfe.toString());
            System.exit(1);
        } catch(Exception | InvalidArgumentException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return routes;
    }

    public String makeFileFromRoute(Stack<Route> routes) {
        Log.logger.log(Level.INFO,"Making csv string");
        StringBuilder csv = new StringBuilder();
        csv.append("name,coordinates,creationDate,from,to,distance\n");
        for (Route route: routes) {
            csv.append(route.toString() + '\n');
        }
        return csv.toString();
    }
}
