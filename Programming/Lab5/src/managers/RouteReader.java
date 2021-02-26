package managers;

import exception.RouteReadException;
import route.Coordinates;
import route.FirstLocation;
import route.SecondLocation;

import java.io.EOFException;

public interface RouteReader {
    public Coordinates readCoordinates() throws RouteReadException;
    public FirstLocation readFirstLocation() throws RouteReadException;
    public SecondLocation readSecondLocation() throws RouteReadException;
    public String readName() throws RouteReadException;
    public double readDistance() throws RouteReadException;
}
