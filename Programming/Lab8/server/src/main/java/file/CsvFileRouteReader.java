package file;

import collection.RouteCollectionManager;
import exception.BadCSVException;
import exception.RouteBuildException;
import locale.ServerBundle;
import log.Log;
import route.*;

import javax.validation.ValidatorFactory;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CsvFileRouteReader implements RouteReader{
    private final RouteCollectionManager routeManager;
    private final String fileName;
    private final ValidatorFactory validatorFactory;

    public CsvFileRouteReader(RouteCollectionManager routeManager, String fileName, ValidatorFactory validatorFactory) {
        this.routeManager = routeManager;
        this.fileName = fileName;
        this.validatorFactory = validatorFactory;
    }

    public List<Route> read() {
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
            Log.getLogger().warn(ServerBundle.getString("exception.csvfile_not_found"));
            createNewFile();
            return read();
        } catch (IOException e) {
            Log.getLogger().error(ServerBundle.getString("exception.csvfile_read_error"));
            Log.getLogger().error(e.getStackTrace());
        }
        return parseRouteFromFile(readResult);
    }

    private void createNewFile() {
        try (
                PrintWriter printWriter = new PrintWriter(new FileWriter(fileName))
        ) {
            printWriter.println("id,name,coordinates,creationDate,from,to,distance");
            Log.getLogger().info(ServerBundle.getString("csvfile_reader.file_created"));
        } catch (IOException ioe) {
            Log.getLogger().error(ServerBundle.getString("exception.csvfile_create_error"));
            Log.getLogger().error(ioe.getStackTrace());
        }
    }

    private List<Route> parseRouteFromFile(List<String> inputString) {
        List<Route> routes = new ArrayList<>();

        try {
            if(!inputString.get(0).replace("\r","").equals("id,name,coordinates,creationDate,from,to,distance"))
                throw new BadCSVException(ServerBundle.getString("exception.bad_csvfile_heading"));
            inputString.remove(0);
            for (String str : inputString) {
                String formattedStr = str.replace("\"", "");
                String[] params = formattedStr.split("\\s*,\\s*");

                if(params.length != 14)
                    throw new BadCSVException(ServerBundle.getString("exception.wrong_csvfile_params"));

                int id = Integer.parseInt(params[0]);

                /*if (!routeManager.addUniqueID(id)) {
                    throw new BadCSVException(ServerBundle.getString("exception.csvfile_duplicate_id"));
                }*/

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
                RouteBuilder routeBuilder = new RouteBuilder(validatorFactory);
                Route route = routeBuilder.setId(id).setName(name)
                        .setCoordinates(coordinates)
                        .setDate(date)
                        .setFirstLocation(firstLocation)
                        .setSecondLocation(secondLocation)
                        .setDistance(dist)
                        .buildWithId();
                routeManager.addRoute(route);
                routes.add(route);
            }
        } catch(NumberFormatException | BadCSVException | RouteBuildException e) {
            Log.getLogger().error(ServerBundle.getFormattedString("exception.bad_csvfile", e.getMessage()));
            Log.getLogger().error(e.getStackTrace());
        }
        return routes;
    }
}
