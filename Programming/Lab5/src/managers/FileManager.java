package managers;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;

import exception.BadCSVException;
import log.Log;
import route.Coordinates;
import route.FirstLocation;
import route.Route;
import route.SecondLocation;
import route.exceptions.InvalidArgumentException;

/**
 * Класс, отвечающий за взаимодействие с файлами
 * @author spynad
 */
public class FileManager implements IOManager {
    private final CollectionRouteManager routeManager;

    private final String fileName;

    public FileManager(CollectionRouteManager routeManager, String fileName) {
        Log.logger.log(Level.INFO,"FileManager init");
        this.routeManager = routeManager;
        this.fileName = fileName;
    }


    /**
     * Метод, читающий файл и возвращающий ArrayList строчек файла
     * @return - массив со строчками файла
     */
    public ArrayList<Route> read() {
        Log.logger.log(Level.INFO,"Reading file " + fileName);
        File file = new File(fileName);
        ArrayList<String> readResult = new ArrayList<>();
        try (
                FileInputStream inputStream = new FileInputStream(file);
                BufferedInputStream buffer = new BufferedInputStream(inputStream)
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
            read();
        } catch (IOException e) {
            System.err.println("An exception occurred while trying to read file: " + e);
        }
        return parseRouteFromFile(readResult);
    }

    public void createNewFile() {
        try (
                FileWriter fileWriter = new FileWriter(fileName);
                PrintWriter printWriter = new PrintWriter(fileWriter)
        ) {
            printWriter.println("id,name,coordinates,creationDate,from,to,distance");
            System.err.println("The file has been created.");
        } catch (IOException ioe) {
            System.err.println("Exception while trying to create a new file");
        }
    }

    /**
     * Метдо, записывающий csv-строчку в файл
     */
    public void write() {
        try (
                FileWriter fileWriter = new FileWriter(fileName);
                PrintWriter printWriter = new PrintWriter(fileWriter)
        ) {
            Log.logger.log(Level.INFO,"Writing csv string to csv file");
            String output = makeFileFromRoute(routeManager.getRoutes());
            printWriter.print(output);

        } catch(IOException ioe) {
            System.err.println("Exception while trying to write to a file");
            Log.logger.log(Level.WARNING, "EXCEPTION: ", ioe);
            System.exit(1);
        }
    }

    private ArrayList<Route> parseRouteFromFile(ArrayList<String> inputString) {
        ArrayList<Route> routes = new ArrayList<>();
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
                Route route = new Route(id, name, coordinates, date, firstLocation, secondLocation, dist);
                routes.add(route);
            }
        } catch(NumberFormatException | BadCSVException nfe) {
            System.err.println("Exception while trying to read CSV file: " + nfe.toString());
            System.err.println("File read is interrupted.");
            Log.logger.log(Level.WARNING, "EXCEPTION: ", nfe);
        } catch(InvalidArgumentException e) {
            System.err.println(e.getMessage());
            System.err.println("File read is interrupted.");
            Log.logger.log(Level.WARNING, "EXCEPTION: ", e);
        }
        return routes;
    }

    private String makeFileFromRoute(Stack<Route> routes) {
        Log.logger.log(Level.INFO,"Making csv string");
        StringBuilder csv = new StringBuilder();
        csv.append("id,name,coordinates,creationDate,from,to,distance\n");
        for (Route route: routes) {
            csv.append(route.toString()).append('\n');
        }
        return csv.toString();
    }
}
