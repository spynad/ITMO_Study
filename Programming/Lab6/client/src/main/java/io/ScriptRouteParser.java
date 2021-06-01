package io;

import exception.RouteBuildException;
import exception.RouteReadException;
import locale.ClientLocale;
import route.*;
import exception.InvalidArgumentException;

import java.io.BufferedReader;
import java.io.IOException;

public class ScriptRouteParser implements SingleRouteReader{
    BufferedReader input;
    UserIO userIO;

    public ScriptRouteParser(BufferedReader reader, UserIO userIO) {
        input = reader;
        this.userIO = userIO;
    }

    @Override
    public Route read() throws RouteBuildException, RouteReadException {
        return new RouteBuilder().setName(readName())
                .setCoordinates(readCoordinates())
                .setFirstLocation(readFirstLocation())
                .setSecondLocation(readSecondLocation())
                .setDistance(readDistance())
                .buildWithoutId();
    }

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
            } catch (IOException ioe) {
                userIO.printErrorMessage(ClientLocale.getString("exception.general"));
            } catch (NumberFormatException nfe) {
                userIO.printErrorMessage(ClientLocale.getString("exception.invalid_format_error"));
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
            } catch (IOException ioe) {
                userIO.printErrorMessage(ClientLocale.getString("exception.general"));
            } catch (NumberFormatException nfe) {
                userIO.printErrorMessage(ClientLocale.getString("exception.invalid_format_error"));
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
            } catch (IOException ioe) {
                userIO.printErrorMessage(ClientLocale.getString("exception.general"));
            } catch (NumberFormatException nfe) {
                userIO.printErrorMessage(ClientLocale.getString("exception.invalid_format_error"));
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

                return str.replaceAll("[^\\w\\s]", "");
            } catch (IOException ioe) {
                userIO.printErrorMessage(ClientLocale.getString("exception.general"));
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
                return Double.parseDouble(str);
            } catch (NumberFormatException nfe) {
                userIO.printErrorMessage(ClientLocale.getString("exception.invalid_format_error"));
            } catch (IOException ioe) {
                userIO.printErrorMessage(ClientLocale.getString("exception.general"));
            }
        }
    }
}
