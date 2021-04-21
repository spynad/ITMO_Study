package route;

import exception.RouteBuildException;
import locale.RouteBundle;

import java.time.LocalDate;

public class RouteBuilder implements Builder{
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private FirstLocation from; //Поле может быть null
    private SecondLocation to; //Поле не может быть null
    private double distance; //Значение поля должно быть больше 1
    private String username;


    @Override
    public RouteBuilder setId(int id) throws RouteBuildException{
        if (id < 0) {
            throw new RouteBuildException(RouteBundle.getString("route.invalid_id"));
        }
        this.id = id;
        return this;
    }

    @Override
    public RouteBuilder setName(String name) throws RouteBuildException {
        if (name.equals("")) {
            throw new RouteBuildException(RouteBundle.getString("route.invalid_name"));
        }
        this.name = name;
        return this;
    }

    @Override
    public RouteBuilder setCoordinates(Coordinates coordinates) throws RouteBuildException {
        if (coordinates != null) {
            if (coordinates.getX() > -776 && coordinates.getY() != null) {
                this.coordinates = coordinates;
                return this;
            }
        }
        throw new RouteBuildException(RouteBundle.getString("route.invalid_coordinates"));
    }

    @Override
    public RouteBuilder setDate(LocalDate localDate) throws RouteBuildException {
        if (localDate == null) {
            throw new RouteBuildException(RouteBundle.getString("route.invalid_date"));
        }
        this.creationDate = localDate;
        return this;
    }

    @Override
    public RouteBuilder setFirstLocation(FirstLocation firstLocation) throws RouteBuildException {
        if (firstLocation == null) {
            this.from = firstLocation;
            return this;
        } else {
            if (firstLocation.getX() != null && !firstLocation.getName().equals("")) {
                this.from = firstLocation;
                return this;
            } else {
                throw new RouteBuildException(RouteBundle.getString("route.invalid_first_location"));
            }
        }
    }

    @Override
    public RouteBuilder setSecondLocation(SecondLocation secondLocation) throws RouteBuildException {
        if (secondLocation != null) {
            if (secondLocation.getX() != null && secondLocation.getY() != null && secondLocation.getZ() != null) {
                this.to = secondLocation;
                return this;
            }
        }
        throw new RouteBuildException(RouteBundle.getString("route.invalid_second_location"));
    }

    @Override
    public RouteBuilder setDistance(double distance) throws RouteBuildException {
        if (distance < 1) {
            throw new RouteBuildException(RouteBundle.getString("route.invalid_distance"));
        }
        this.distance = distance;
        return this;
    }

    @Override
    public RouteBuilder setUsername(String username) throws RouteBuildException {
        this.username = username;
        return this;
    }

    @Override
    public Route buildWithId() {
        return new Route(id, name, coordinates, from, to, distance);
    }

    @Override
    public Route buildWithoutId() {
        return new Route(name, coordinates, from, to, distance);
    }


}
