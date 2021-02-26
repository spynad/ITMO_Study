package managers;

import log.Log;
import main.Application;
import route.Coordinates;
import route.FirstLocation;
import route.SecondLocation;
import route.exceptions.InvalidArgumentException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;

/**
 * Класс, обеспечивающий считывание полей объектов Route из глобального потока ввода
 *
 * @author spynad
 */
public class ConsoleRouteReader implements RouteReader {
    BufferedReader input = Application.getInput();


    /**
     * Метод, который считывает поля объекта Coordinates и возвращает его
     *
     * @return - объект Coordinates
     */
    public Coordinates readCoordinates() {
        while (true) {
            try {
                System.out.println("enter coordinates.x: ");
                long x = Long.parseLong(input.readLine());

                System.out.println("enter coordinates.y: ");
                double y = Double.parseDouble(input.readLine());

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
                System.out.println("enter firstlocation.x: ");
                int x = Integer.parseInt(input.readLine());

                System.out.println("enter firstlocation.y: ");
                long y = Long.parseLong(input.readLine());

                System.out.println("enter firstlocation.name: ");
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
                System.out.println("enter secondlocation.x: ");
                int x = Integer.parseInt(input.readLine());

                System.out.println("enter secondlocation.y: ");
                long y = Long.parseLong(input.readLine());

                System.out.println("enter secondlocation.z: ");
                double z = Double.parseDouble(input.readLine());

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
                System.out.println("enter name: ");
                String str = input.readLine();
                String formStr = str.replaceAll("[^\\w\\s]", "");
                return formStr;
            } catch (IOException ioe) {
                System.err.println("something happened");
                Log.logger.log(Level.WARNING, "EXCEPTION: ", ioe);
            }
        }
    }

    public double readDistance() {
        while (true) {
            try {
                System.out.println("enter distance: ");
                String str = input.readLine();
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
