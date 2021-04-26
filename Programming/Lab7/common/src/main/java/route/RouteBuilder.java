package route;

import exception.RouteBuildException;
import locale.CommonBundle;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

public class RouteBuilder implements Builder{

    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private FirstLocation from; //Поле может быть null
    private SecondLocation to; //Поле не может быть null
    private double distance; //Значение поля должно быть больше 1
    private String username;
    private final Validator validator;

    public RouteBuilder(ValidatorFactory validatorFactory) {
        validator = validatorFactory.getValidator();
    }


    @Override
    public RouteBuilder setId(int id) throws RouteBuildException{
        Set<ConstraintViolation<Route>> violations =  validator.validateValue(Route.class, "id", id);
        if (violations.isEmpty()) {
            this.id = id;
            return this;
        }
        throw new RouteBuildException(CommonBundle.getString("route.invalid_id"));
    }

    @Override
    public RouteBuilder setName(String name) throws RouteBuildException {
        Set<ConstraintViolation<Route>> violations =  validator.validateValue(Route.class, "name", name);
        if (violations.isEmpty()) {
            this.name = name;
            return this;
        }
        throw new RouteBuildException(CommonBundle.getString("route.invalid_name"));
    }

    @Override
    public RouteBuilder setCoordinates(Coordinates coordinates) throws RouteBuildException {
        Set<ConstraintViolation<Route>> violations =  validator.validateValue(Route.class, "coordinates", coordinates);
        if (violations.isEmpty()) {
            this.coordinates = coordinates;
            return this;
        }
        throw new RouteBuildException(CommonBundle.getString("route.invalid_coordinates"));
    }

    @Override
    public RouteBuilder setDate(LocalDate localDate) throws RouteBuildException {
        Set<ConstraintViolation<Route>> violations =  validator.validateValue(Route.class, "creationDate", localDate);
        if (violations.isEmpty()) {
            this.creationDate = localDate;
            return this;
        }
        throw new RouteBuildException(CommonBundle.getString("route.invalid_date"));
    }

    @Override
    public RouteBuilder setFirstLocation(FirstLocation firstLocation) throws RouteBuildException {
        Set<ConstraintViolation<Route>> violations =  validator.validateValue(Route.class, "from", firstLocation);
        if (violations.isEmpty()) {
            this.from = firstLocation;
            return this;
        }
        throw new RouteBuildException(CommonBundle.getString("route.invalid_first_location"));
        /*if (firstLocation == null) {
            this.from = firstLocation;
            return this;
        } else {
            if (firstLocation.getX() != null && !firstLocation.getName().equals("")) {
                this.from = firstLocation;
                return this;
            } else {
                throw new RouteBuildException(RouteBundle.getString("route.invalid_first_location"));
            }
        }*/
    }

    @Override
    public RouteBuilder setSecondLocation(SecondLocation secondLocation) throws RouteBuildException {
        Set<ConstraintViolation<Route>> violations =  validator.validateValue(Route.class, "to", secondLocation);
        if (violations.isEmpty()) {
            this.to = secondLocation;
            return this;
        }
        throw new RouteBuildException(CommonBundle.getString("route.invalid_second_location"));
        /*if (secondLocation != null) {
            if (secondLocation.getX() != null && secondLocation.getY() != null && secondLocation.getZ() != null) {
                this.to = secondLocation;
                return this;
            }
        }
        throw new RouteBuildException(RouteBundle.getString("route.invalid_second_location"));*/
    }

    @Override
    public RouteBuilder setDistance(double distance) throws RouteBuildException {
        Set<ConstraintViolation<Route>> violations =  validator.validateValue(Route.class, "distance", distance);
        if (violations.isEmpty()) {
            this.distance = distance;
            return this;
        }
        throw new RouteBuildException(CommonBundle.getString("route.invalid_distance"));
        /*if (distance < 1) {
            throw new RouteBuildException(RouteBundle.getString("route.invalid_distance"));
        }
        this.distance = distance;
        return this;*/
    }

    @Override
    public RouteBuilder setUsername(String username) throws RouteBuildException {
        this.username = username;
        return this;
    }

    @Override
    public Route buildWithId() {
        return new Route(id, name, coordinates, from, to, distance, username);
    }

    @Override
    public Route buildWithoutId() {
        return new Route(name, coordinates, from, to, distance, username);
    }


}
