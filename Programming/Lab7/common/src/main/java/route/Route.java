package route;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Formatter;
import java.util.Locale;

import exception.InvalidArgumentException;

/**
 * Класс, который хранится в коллекции
 * @author spynad
 * @version govno
 */
public final class Route implements Comparable<Route>, Serializable {
    private static final long serialVersionUID = 5913735705352552388L;

    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private FirstLocation from; //Поле может быть null
    private SecondLocation to; //Поле не может быть null
    private double distance; //Значение поля должно быть больше 1
    private String username;

    /**
     * уникальный ID объекта
     */
    private static Integer uniqueId = 0;

    public Route(String name,
                 Coordinates coordinates,
                 FirstLocation from,
                 SecondLocation to,
                 double distance) {
        id = uniqueId + 1;
        uniqueId++;
        this.name = name;
        this.coordinates = coordinates;
        creationDate = LocalDate.now();
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public Route(int id,
                 String name,
                 Coordinates coordinates,
                 LocalDate date,
                 FirstLocation from,
                 SecondLocation to,
                 double distance) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        creationDate = date;
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public Route(int id,
                 String name,
                 Coordinates coordinates,
                 FirstLocation from,
                 SecondLocation to,
                 double distance) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        creationDate = LocalDate.now();
        this.from = from;
        this.to = to;
        this.distance = distance;
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

    public String getUsername() {
        return username;
    }

    public void setId(int id) {
        this.id = id;
    }


    /**
     * Метод, который валидирует все поля объекта Route
     * @return - true, если валидация прошла успешно, иначе - false
     */
    @Override
    public int compareTo(Route o) {
        return getName().compareTo(o.getName());
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
        f.format("%d,\"%s\",%s,\"%s,%s,%s\",%s,%s,%s",
                id, name, coordinates.toString(), creationDate.getYear(),
                creationDate.getMonthValue(), creationDate.getDayOfMonth(),
                fr, to.toString(), String.format(Locale.ROOT,"%.2f",distance));
        return f.toString();
    }
}