package managers;

import exception.RouteReadException;
import log.Log;
import route.Coordinates;
import route.FirstLocation;
import route.SecondLocation;
import route.exceptions.InvalidArgumentException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;

public class FileRouteReader implements RouteReader{
    BufferedReader input;

    public FileRouteReader(BufferedReader reader) {
        input = reader;
    }
    /**
     * Метод, который считывает поля объекта Coordinates и возвращает его
     *
     * @return - объект Coordinates
     */
    public Coordinates readCoordinates() throws RouteReadException{
        while (true) {
            try {
                long x;
                double y;
                if(input.ready()) {
                    x = Long.parseLong(input.readLine());
                } else {
                    throw new RouteReadException();
                }

                if(input.ready()) {
                    y = Double.parseDouble(input.readLine());
                } else {
                    throw new RouteReadException();
                }

                return new Coordinates(x, y);
            } catch (InvalidArgumentException e) {
                System.err.println(e.getMessage());
                Log.logger.log(Level.WARNING, "EXCEPTION: ", e);
            } catch (IOException ioe) {
                System.err.println("read error");
                Log.logger.log(Level.WARNING, "EXCEPTION: ", ioe);
            } catch (NumberFormatException nfe) {
                System.err.println("invalid arguments");
                Log.logger.log(Level.WARNING, "EXCEPTION: ", nfe);
            }
        }
    }

    /**
     * Метод, который считывает поля объекта FirstLocation и возвращает его
     *
     * @return - объект FirstLocation
     */
    public FirstLocation readFirstLocation() throws RouteReadException{
        while (true) {
            try {
                int x;
                long y;
                if(input.ready()) {
                    x = Integer.parseInt(input.readLine());
                } else {
                    throw new RouteReadException();
                }

                if(input.ready()) {
                    y = Long.parseLong(input.readLine());
                } else {
                    throw new RouteReadException();
                }

                String readName = readName();

                return new FirstLocation(x, y, readName);
            } catch (InvalidArgumentException e) {
                System.err.println(e.getMessage());
                Log.logger.log(Level.WARNING, "EXCEPTION: ", e);
            } catch (IOException ioe) {
                System.err.println("read error");
                Log.logger.log(Level.WARNING, "EXCEPTION: ", ioe);
            } catch (NumberFormatException nfe) {
                System.err.println("invalid arguments");
                Log.logger.log(Level.WARNING, "EXCEPTION: ", nfe);
            }
        }
    }

    /**
     * Метод, который считывает поля объекта SecondLocation и возвращает его
     *
     * @return - объект SecondLocation
     */
    public SecondLocation readSecondLocation() throws RouteReadException{
        while (true) {
            try {
                int x;
                long y;
                double z;
                if(input.ready()) {
                    x = Integer.parseInt(input.readLine());
                } else {
                    throw new RouteReadException();
                }

                if (input.ready()) {
                    y = Long.parseLong(input.readLine());
                } else {
                    throw new RouteReadException();
                }

                if (input.ready()) {
                    z = Double.parseDouble(input.readLine());
                } else {
                    throw new RouteReadException();
                }

                return new SecondLocation(x, y, z);
            } catch (InvalidArgumentException e) {
                System.err.println(e.getMessage());
                Log.logger.log(Level.WARNING, "EXCEPTION: ", e);
            } catch (IOException ioe) {
                System.err.println("read error");
                Log.logger.log(Level.WARNING, "EXCEPTION: ", ioe);
            } catch (NumberFormatException nfe) {
                System.err.println("invalid arguments");
                Log.logger.log(Level.WARNING, "EXCEPTION: ", nfe);
            }
        }
    }

    public String readName() throws RouteReadException{
        while(true) {
            try {
                String str;
                if (input.ready()) {
                    str = input.readLine();
                } else {
                    throw new RouteReadException();
                }

                String formStr = str.replaceAll("[^\\w\\s]", "");
                return formStr;
            } catch (IOException ioe) {
                System.err.println("something happened");
                Log.logger.log(Level.WARNING, "EXCEPTION: ", ioe);
            }
        }
    }

    public double readDistance() throws RouteReadException{
        while (true) {
            try {
                String str;
                if(input.ready()) {
                    str = input.readLine();
                } else {
                    throw new RouteReadException();
                }
                double distance = Double.parseDouble(str);
                return distance;
            } catch (NumberFormatException nfe) {
                System.err.println("invalid argument");
                Log.logger.log(Level.WARNING, "EXCEPTION: ", nfe);
            } catch (IOException ioe) {
                System.err.println("something happened");
                Log.logger.log(Level.WARNING, "EXCEPTION: ", ioe);
            }
        }
    }
}
