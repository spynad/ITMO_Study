package route;

import java.time.LocalDate;
import java.util.Formatter;
import java.util.Locale;

import route.exceptions.InvalidArgumentException;

/**
 * Класс, который хранится в коллекции
 * @author spynad
 * @version govno
 */
public final class Route implements Comparable<Route>{
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private FirstLocation from; //Поле может быть null
    private SecondLocation to; //Поле не может быть null
    private double distance; //Значение поля должно быть больше 1

    /**
     * уникальный ID объекта
     */
    private static Integer uniqueId = 0;

    public Route(String name,
                 Coordinates coordinates,
                 FirstLocation from,
                 SecondLocation to,
                 double distance) throws InvalidArgumentException{
        id = uniqueId + 1;
        uniqueId++;
        this.name = name;
        this.coordinates = coordinates;
        creationDate = LocalDate.now();
        this.from = from;
        this.to = to;
        this.distance = distance;
        if (!validateRoute())
            throw new InvalidArgumentException("invalid route");
    }

    public Route(int id,
                 String name,
                 Coordinates coordinates,
                 LocalDate date,
                 FirstLocation from,
                 SecondLocation to,
                 double distance) throws InvalidArgumentException{
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        creationDate = date;
        this.from = from;
        this.to = to;
        this.distance = distance;
        if (!validateRoute())
            throw new InvalidArgumentException("invalid route");
    }

    public Route(int id,
                 String name,
                 Coordinates coordinates,
                 FirstLocation from,
                 SecondLocation to,
                 double distance) throws InvalidArgumentException{
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        creationDate = LocalDate.now();
        this.from = from;
        this.to = to;
        this.distance = distance;
        if (!validateRoute())
            throw new InvalidArgumentException("invalid route");
    }

    public Integer getId() {
        return id;
    }

    public double getDistance() {
        return distance;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public SecondLocation getTo() {
        return to;
    }

    public FirstLocation getFrom() {
        return from;
    }

    public boolean validateId() {
        return (id != null) && (id > 0);
    }

    public boolean validateName() {
        return (!name.equals(""));
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

    /**
     * Метод, который валидирует все поля объекта Route
     * @return - true, если валидация прошла успешно, иначе - false
     */
    public boolean validateRoute() {
        return validateId() && validateName() && validateCoordinates() && validateDate() && validateSecondLocation()
                && validateDistance();
    }
    @Override
    public int compareTo(Route o) {
        return getId() - o.getId();
    }

    @Override
    public String toString() {
        Formatter f = new Formatter();
        String fr;
        if(from == null) {
            fr = "null, null, null";
        } else {
            fr = from.toString();
        }
        f.format("%d,%s,%s,\"%s,%s,%s\",%s,%s,%s",
                id, name, coordinates.toString(), creationDate.getYear(),
                creationDate.getMonthValue(), creationDate.getDayOfMonth(),
                fr, to.toString(), String.format(Locale.ROOT,"%.2f",distance));
        return f.toString();
    }
}