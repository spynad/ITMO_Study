package main;

import exception.BadCSVException;
import exception.InvalidArgumentException;
import exception.RouteBuildException;
import log.Log;
import route.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class CsvFileRouteReader implements RouteReader{
    private final RouteCollectionManager routeManager;

    private final String fileName;

    public CsvFileRouteReader(RouteCollectionManager routeManager, String fileName) {
        Log.logger.log(Level.INFO,"FileManager init");
        this.routeManager = routeManager;
        this.fileName = fileName;
    }

    public List<Route> read() {
        Log.logger.log(Level.INFO,"Reading file " + fileName);
        File file = new File(fileName);
        ArrayList<String> readResult = new ArrayList<>();
        try (
                BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(file))
        ) {
            StringBuilder readStringBuilder = new StringBuilder();

            while (buffer.available() > 0) {
                char nextChar = (char) buffer.read();
                readStringBuilder.append(nextChar);

                if (nextChar == '\n') {
                    readStringBuilder.delete(readStringBuilder.length() - 1, readStringBuilder.length());
                    if(!readStringBuilder.toString().equals(""))
                        readResult.add(readStringBuilder.toString());
                    readStringBuilder.delete(0, readStringBuilder.length());
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("The file not found, creating a new one.");
            createNewFile();
            return read();
        } catch (IOException e) {
            System.err.println("An exception occurred while trying to read file: " + e);
        }
        return parseRouteFromFile(readResult);
    }

    private void createNewFile() {
        try (
                PrintWriter printWriter = new PrintWriter(new FileWriter(fileName))
        ) {
            printWriter.println("id,name,coordinates,creationDate,from,to,distance");
            System.err.println("The file has been created.");
        } catch (IOException ioe) {
            System.err.println("Exception while trying to create a new file");
        }
    }

    private List<Route> parseRouteFromFile(List<String> inputString) {
        List<Route> routes = new ArrayList<>();
        Log.logger.log(Level.INFO,"Parsing file");

        try {
            if(!inputString.get(0).replace("\r","").equals("id,name,coordinates,creationDate,from,to,distance"))
                throw new BadCSVException("bad csv format");
            inputString.remove(0);
            for (String str : inputString) {
                String formattedStr = str.replace("\"", "");
                String[] params = formattedStr.split("\\s*,\\s*");

                if(params.length != 14)
                    throw new BadCSVException("shit with params.length");

                int id = Integer.parseInt(params[0]);

                if (!routeManager.addUniqueID(id)) {
                    throw new BadCSVException("duplicate id found");
                }

                if (routeManager.getRoutes().size() == Integer.MAX_VALUE) {
                    throw new BadCSVException("id threshold reached");
                }
                String name = params[1];
                Coordinates coordinates = new Coordinates(Long.parseLong(params[2]),
                        Double.parseDouble(params[3]));
                LocalDate date = LocalDate.of(Integer.parseInt(params[4]),
                        Integer.parseInt(params[5]),
                        Integer.parseInt(params[6]));

                FirstLocation firstLocation;
                if (params[7].equals("null") && params[8].equals("null") && params[9].equals("null"))
                {
                    firstLocation = null;
                } else {
                    firstLocation = new FirstLocation(Integer.parseInt(params[7]),
                            Long.parseLong(params[8]),
                            params[9]);
                }

                SecondLocation secondLocation = new SecondLocation(Integer.parseInt(params[10]),
                        Long.parseLong(params[11]),
                        Double.parseDouble(params[12]));
                double dist = Double.parseDouble(params[13]);
                RouteBuilder routeBuilder = new RouteBuilder();
                Route route = routeBuilder.setId(id)
                        .setName(name)
                        .setCoordinates(coordinates)
                        .setDate(date)
                        .setFirstLocation(firstLocation)
                        .setSecondLocation(secondLocation)
                        .setDistance(dist)
                        .buildWithId();
                routes.add(route);
            }
        } catch(NumberFormatException | BadCSVException nfe) {
            System.err.println("Exception while trying to read CSV file: " + nfe.toString());
            System.err.println("File read is interrupted.");
            Log.logger.log(Level.WARNING, "EXCEPTION: ", nfe);
        } catch(InvalidArgumentException | RouteBuildException e) {
            System.err.println(e.getMessage());
            System.err.println("File read is interrupted.");
            Log.logger.log(Level.WARNING, "EXCEPTION: ", e);
        }
        return routes;
    }
}
