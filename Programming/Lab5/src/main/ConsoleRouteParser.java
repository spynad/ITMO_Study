package main;

import exception.RouteBuildException;
import io.UserIO;
import log.Log;
import main.Application;
import main.RouteCollectionManager;
import main.SingleRouteReader;
import route.Coordinates;
import route.FirstLocation;
import route.Route;
import route.SecondLocation;
import exception.InvalidArgumentException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;

/**
 * Класс, обеспечивающий считывание полей объектов Route из глобального потока ввода
 *
 * @author spynad
 */
public class ConsoleRouteParser implements SingleRouteReader {
    UserIO userIO;
    RouteCollectionManager routeManager;

    public ConsoleRouteParser(RouteCollectionManager routeManager, UserIO userIO) {
        this.routeManager = routeManager;
        this.userIO = userIO;
    }

    public Route read() throws RouteBuildException {
        Route route;
        route = routeManager.createRoute(readName(),readCoordinates(),readFirstLocation(),readSecondLocation(),readDistance());
        return route;
    }

    /**
     * Метод, который считывает поля объекта Coordinates и возвращает его
     *
     * @return - объект Coordinates
     *
     */
    public Coordinates readCoordinates() {
        while (true) {
            try {
                userIO.printLine("enter coordinates.x: ");
                long x = Long.parseLong(userIO.readLine());

                userIO.printLine("enter coordinates.y: ");
                double y = Double.parseDouble(userIO.readLine());

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
    public FirstLocation readFirstLocation() {
        while (true) {
            try {
                userIO.printLine("enter firstlocation.x: ");
                int x = Integer.parseInt(userIO.readLine());

                userIO.printLine("enter firstlocation.y: ");
                long y = Long.parseLong(userIO.readLine());

                userIO.printLine("enter firstlocation.name: ");
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
    public SecondLocation readSecondLocation() {
        while (true) {
            try {
                userIO.printLine("enter secondlocation.x: ");
                int x = Integer.parseInt(userIO.readLine());

                userIO.printLine("enter secondlocation.y: ");
                long y = Long.parseLong(userIO.readLine());

                userIO.printLine("enter secondlocation.z: ");
                double z = Double.parseDouble(userIO.readLine());

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

    public String readName() {
        while (true) {
            try {
                userIO.printLine("enter name: ");
                String str = userIO.readLine();
                return str.replaceAll("[^\\w\\s]", "");
            } catch (IOException ioe) {
                System.err.println("something happened");
                Log.logger.log(Level.WARNING, "EXCEPTION: ", ioe);
            }
        }
    }

    public double readDistance() {
        while (true) {
            try {
                userIO.printLine("enter distance: ");
                String str = userIO.readLine();
                return Double.parseDouble(str);
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
