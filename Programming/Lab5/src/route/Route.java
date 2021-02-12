package route;

import java.time.LocalDate;
import java.util.Formatter;
import java.util.Locale;

import route.exceptions.InvalidArgumentException;

public class Route implements Comparable<Route>{
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private FirstLocation from; //Поле может быть null
    private SecondLocation to; //Поле не может быть null
    private double distance; //Значение поля должно быть больше 1

    private static Integer uniqueId = 1;

    //TODO: объединить валидацию полей класса в один метод, так должно быть красивее
    public Route(String name,
                 Coordinates coordinates,
                 FirstLocation from,
                 SecondLocation to,
                 double distance) throws InvalidArgumentException{
        id = uniqueId++;
        this.name = name;
        this.coordinates = coordinates;
        creationDate = LocalDate.now();
        this.from = from;
        this.to = to;
        this.distance = distance;
        if (!validateRoute())
            throw new InvalidArgumentException();
    }

    public Route(String name,
                 Coordinates coordinates,
                 LocalDate date,
                 FirstLocation from,
                 SecondLocation to,
                 double distance) throws InvalidArgumentException{
        id = uniqueId++;
        this.name = name;
        this.coordinates = coordinates;
        creationDate = date;
        this.from = from;
        this.to = to;
        this.distance = distance;
        if (!validateRoute())
            throw new InvalidArgumentException();
    }

    public Integer getId() {
        return id;
    }

    public boolean validateId() {
        return (id != null) && (id > 0);
    }

    public boolean validateName() {
        return (!name.equals("")) && (name != null);
    }

    public boolean validateCoordinates() {
        return coordinates != null;
    }

    public boolean validateDate() {
        return creationDate != null;
    }

    public boolean validateSecondLocation() {
        return to != null;
    }

    public boolean validateDistance() {
        return distance > 1;
    }

    public boolean validateRoute() {
        return validateId() && validateName() && validateCoordinates() && validateDate() && validateSecondLocation()
                && validateDistance();
    }
    @Override
    public int compareTo(Route o) {
        return id.compareTo(o.getId());
    }

    @Override
    public String toString() {
        Formatter f = new Formatter();
        f.format("%s,%s,\"%s,%s,%s\",%s,%s,%s",
                name, coordinates.toString(), creationDate.getYear(),
                creationDate.getMonthValue(), creationDate.getDayOfMonth(),
                from.toString(), to.toString(), String.format(Locale.ROOT,"%.2f",distance));
        return f.toString();
    }
}