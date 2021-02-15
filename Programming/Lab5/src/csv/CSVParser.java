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
import java.util.*;
import java.util.logging.Level;

public class CSVParser implements Parser{
    Set<Integer> setId = new HashSet<>();

    public CSVParser() {
        Log.logger.log(Level.INFO,"CSVReader object created");
    }
    //TODO: это пиздец, переделать это дерьмо
    public ArrayList<Route> parseRouteFromFile(ArrayList<String> inputString) {
        ArrayList<Route> routes = new ArrayList<>();
        Log.logger.log(Level.INFO,"Parsing file");
        try {
            if(!inputString.get(0).equals("id,name,coordinates,creationDate,from,to,distance"))
                throw new BadCSVException();
            inputString.remove(0);
            for (String str : inputString) {
                String formattedStr = str.replace("\"", "");
                String[] params = formattedStr.split(",");
                if(params.length != 14)
                    throw new BadCSVException();
                int id = Integer.parseInt(params[0]);
                if (setId.add(id)) {
                    throw new BadCSVException();
                }
                String name = params[1];
                Coordinates coordinates = new Coordinates(Long.parseLong(params[2]),
                        Double.parseDouble(params[3]));
                LocalDate date = LocalDate.of(Integer.parseInt(params[4]),
                        Integer.parseInt(params[5]),
                        Integer.parseInt(params[6]));
                FirstLocation floc;
                if (params[7].equals("null") && params[8].equals("null") && params[9].equals("null"))
                {
                    floc = null;
                } else {
                    floc = new FirstLocation(Integer.parseInt(params[7]),
                            Long.parseLong(params[8]),
                            params[9]);
                }

                SecondLocation sloc = new SecondLocation(Integer.parseInt(params[10]),
                        Long.parseLong(params[11]),
                        Double.parseDouble(params[12]));
                double dist = Double.parseDouble(params[13]);
                Route route = new Route(id, name, coordinates, date, floc, sloc, dist);
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
        csv.append("id,name,coordinates,creationDate,from,to,distance\n");
        for (Route route: routes) {
            csv.append(route.toString()).append('\n');
        }
        return csv.toString();
    }
}
