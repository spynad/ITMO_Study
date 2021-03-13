package io;

import exception.RouteBuildException;
import io.UserIO;
import route.*;
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

    public ConsoleRouteParser(UserIO userIO) {
        this.userIO = userIO;
    }

    public Route read() throws RouteBuildException {
        RouteBuilder routeBuilder = new RouteBuilder();
        return routeBuilder.setName(readName())
                .setCoordinates(readCoordinates())
                .setFirstLocation(readFirstLocation())
                .setSecondLocation(readSecondLocation())
                .setDistance(readDistance())
                .buildWithoutId();
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
            } catch (IOException ioe) {
                System.err.println("read error");
            } catch (NumberFormatException nfe) {
                System.err.println("invalid arguments");
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
            } catch (IOException ioe) {
                System.err.println("read error");
            } catch (NumberFormatException nfe) {
                System.err.println("invalid arguments");
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
            } catch (IOException ioe) {
                System.err.println("read error");
            } catch (NumberFormatException nfe) {
                System.err.println("invalid arguments");
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
            } catch (IOException ioe) {
                System.err.println("something happened");
            }
        }
    }
}
