package route;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Formatter;
import java.util.Locale;
import java.util.Optional;

import exception.InvalidArgumentException;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Класс, который хранится в коллекции
 * @author spynad
 * @version govno
 */
public final class Route implements Comparable<Route>, Serializable {
    private static final long serialVersionUID = 5913735705352552388L;

    @NotNull
    @Min(value = 1)
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    @NotNull
    @Size(min = 1)
    private String name; //Поле не может быть null, Строка не может быть пустой

    @NotNull
    private Coordinates coordinates; //Поле не может быть null

    @NotNull
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    private FirstLocation from; //Поле может быть null

    @NotNull
    private SecondLocation to; //Поле не может быть null

    @Min(value = 2)
    private double distance; //Значение поля должно быть больше 1

    @NotNull
    private String username;

    /**
     * уникальный ID объекта
     */
    private static Integer uniqueId = 0;

    public Route(String name,
                 Coordinates coordinates,
                 FirstLocation from,
                 SecondLocation to,
                 double distance,
                 String username) {
        id = uniqueId + 1;
        uniqueId++;
        this.name = name;
        this.coordinates = coordinates;
        creationDate = LocalDate.now();
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.username = username;
    }

    public Route(int id,
                 String name,
                 Coordinates coordinates,
                 LocalDate date,
                 FirstLocation from,
                 SecondLocation to,
                 double distance,
                 String username) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        creationDate = date;
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.username = username;
    }

    public Route(int id,
                 String name,
                 Coordinates coordinates,
                 FirstLocation from,
                 SecondLocation to,
                 double distance,
                 String username) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        creationDate = LocalDate.now();
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public double getDistance() {
        return distance;
    }

    public String getLocalizedDistance() {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault());
        return numberFormat.format(distance);
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

    public String getLocalizedCreationDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(Locale.getDefault());
        return formatter.format(creationDate);
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

    public Long getCoordinatesX() {
        return coordinates.getX();
    }

    public Double getCoordinatesY() {
        return coordinates.getY();
    }

    public String getLocalizedCoordinatesY() {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault());
        return numberFormat.format(getCoordinatesY());
    }

    public String getFromX() {
        if (from == null) {
            return "null";
        }
        return Optional.ofNullable(String.valueOf(from.getX())).orElse("null");
    }

    public String getFromY() {
        if (from == null) {
            return "null";
        }
        return Optional.ofNullable(String.valueOf(from.getY())).orElse("null");
    }

    public String getFromName() {
        if (from == null) {
            return "null";
        }
        return Optional.ofNullable(String.valueOf(from.getName())).orElse("null");
    }

    public Integer getToX() {
        return to.getX();
    }

    public Long getToY() {
        return to.getY();
    }

    public Double getToZ() {
        return to.getZ();
    }

    public String getLocalizedToZ() {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault());
        return numberFormat.format(to.getZ());
    }

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