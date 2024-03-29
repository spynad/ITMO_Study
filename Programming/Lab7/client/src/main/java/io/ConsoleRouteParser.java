package io;

import client.AuthModule;
import exception.RouteBuildException;
import locale.ClientLocale;
import route.*;
import exception.InvalidArgumentException;

import javax.validation.ValidatorFactory;
import java.io.IOException;

/**
 * Класс, обеспечивающий считывание полей объектов Route из глобального потока ввода
 *
 * @author spynad
 */
public class ConsoleRouteParser implements SingleRouteReader {
    private final UserIO userIO;
    private final AuthModule authModule;
    private String inputRequest;
    private String invalidFormat;
    private final ValidatorFactory validatorFactory;

    public ConsoleRouteParser(UserIO userIO, AuthModule authModule, ValidatorFactory validatorFactory) {
        this.userIO = userIO;
        this.authModule = authModule;
        inputRequest = ClientLocale.getString("client.input_request");
        invalidFormat = ClientLocale.getString("exception.invalid_format_error");
        this.validatorFactory = validatorFactory;
    }

    public Route read() throws RouteBuildException {
        inputRequest = ClientLocale.getString("client.input_request");
        invalidFormat = ClientLocale.getString("exception.invalid_format_error");
        RouteBuilder routeBuilder = new RouteBuilder(validatorFactory);
        return routeBuilder.setName(readName())
                .setCoordinates(readCoordinates())
                .setFirstLocation(readFirstLocation())
                .setSecondLocation(readSecondLocation())
                .setDistance(readDistance())
                .setUsername(authModule.getUser().getUsername())
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
        try {
            if (userIO.askYesOrNo(ClientLocale.getString("client.ask_for_first_location_null"))) {
                return null;
            }
        } catch (IOException e) {
            userIO.printErrorMessage(ClientLocale.getString("exception.general"));
        }
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
