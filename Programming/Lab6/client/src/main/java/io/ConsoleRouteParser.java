package io;

import exception.RouteBuildException;
import locale.ClientLocale;
import route.*;
import exception.InvalidArgumentException;

import java.io.IOException;

/**
 * Класс, обеспечивающий считывание полей объектов Route из глобального потока ввода
 *
 * @author spynad
 */
public class ConsoleRouteParser implements SingleRouteReader {
    private final UserIO userIO;
    private final String inputRequest;
    private final String invalidFormat;

    public ConsoleRouteParser(UserIO userIO) {
        this.userIO = userIO;
        inputRequest = ClientLocale.getString("client.input_request");
        invalidFormat = ClientLocale.getString("exception.invalid_format_error");
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
                userIO.printLine(inputRequest + " coordinates.x: ");
                long x = Long.parseLong(userIO.readLine());

                userIO.printLine(inputRequest + " coordinates.y: ");
                double y = Double.parseDouble(userIO.readLine());

                return new Coordinates(x, y);
            } catch (IOException ioe) {
                userIO.printErrorMessage(ClientLocale.getString("exception.general"));
            } catch (NumberFormatException nfe) {
                userIO.printErrorMessage(invalidFormat);
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
                userIO.printLine(inputRequest + " firstlocation.x: ");
                int x = Integer.parseInt(userIO.readLine());

                userIO.printLine(inputRequest + " firstlocation.y: ");
                long y = Long.parseLong(userIO.readLine());

                userIO.printLine(inputRequest + " firstlocation.name: ");
                String readName = readName();

                return new FirstLocation(x, y, readName);
            } catch (IOException ioe) {
                userIO.printErrorMessage(ClientLocale.getString("exception.general"));
            } catch (NumberFormatException nfe) {
                userIO.printErrorMessage(invalidFormat);
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
                userIO.printLine(inputRequest + " secondlocation.x: ");
                int x = Integer.parseInt(userIO.readLine());

                userIO.printLine(inputRequest + " secondlocation.y: ");
                long y = Long.parseLong(userIO.readLine());

                userIO.printLine(inputRequest + " secondlocation.z: ");
                double z = Double.parseDouble(userIO.readLine());

                return new SecondLocation(x, y, z);
            } catch (IOException ioe) {
                userIO.printErrorMessage(ClientLocale.getString("exception.general"));
            } catch (NumberFormatException nfe) {
                userIO.printErrorMessage(invalidFormat);
            }
        }
    }

    public String readName() {
        while (true) {
            try {
                userIO.printLine(inputRequest + " name: ");
                String str = userIO.readLine();
                return str.replaceAll("[^\\w\\s]", "");
            } catch (IOException ioe) {
                userIO.printErrorMessage(ClientLocale.getString("exception.general"));
            }
        }
    }

    public double readDistance() {
        while (true) {
            try {
                userIO.printLine(inputRequest + " distance: ");
                String str = userIO.readLine();
                return Double.parseDouble(str);
            } catch (NumberFormatException nfe) {
                userIO.printErrorMessage(invalidFormat);
            } catch (IOException ioe) {
                userIO.printErrorMessage(ClientLocale.getString("exception.general"));
            }
        }
    }
}
