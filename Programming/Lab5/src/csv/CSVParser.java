package csv;

import csv.exceptions.BadCSVException;
import log.Log;
import route.Coordinates;
import route.FirstLocation;
import route.Route;
import route.SecondLocation;
import route.exceptions.InvalidArgumentException;

import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;

/**
 * Класс, реализующий парсинг CSV-файла
 * @author spynad
 * @version govno
 */
public class CSVParser implements Parser{
    /**
     * Множество элементов ID для проверки на дубликаты и лимиты
     */
    Set<Integer> setId = new HashSet<>();

    /**
     * Конструктор {@link CSVParser}
     */
    public CSVParser() {
        Log.logger.log(Level.INFO,"CSVReader object created");
    }

    /**
     * Метод, реализующий парсинг Route с CSV-строчки и возвращающая массив с объектами Route
     * @param inputString - строчка, полученная из CSV-строчки
     * @return - массив Route
     */
    public ArrayList<Route> parseRouteFromFile(ArrayList<String> inputString) {
        ArrayList<Route> routes = new ArrayList<>();
        Log.logger.log(Level.INFO,"Parsing file");
        try {
            if(!inputString.get(0).equals("id,name,coordinates,creationDate,from,to,distance"))
                throw new BadCSVException("bad csv format");
            inputString.remove(0);
            for (String str : inputString) {
                String formattedStr = str.replace("\"", "");
                String[] params = formattedStr.split("\\s*,\\s*");
                if(params.length != 14)
                    throw new BadCSVException("shit with params.length");
                int id = Integer.parseInt(params[0]);
                if (!setId.add(id)) {
                    throw new BadCSVException("duplicate id found");
                }
                if (setId.size() == Integer.MAX_VALUE) {
                    throw new BadCSVException("id threshold reached");
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
        } catch(InvalidArgumentException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return routes;
    }

    /**
     * Метод, создающий csv-строку для записи в файл из коллекции Stack объектов Route
     * @param routes - коллекция Stack типа Route
     * @return - csv-строчка
     */
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
