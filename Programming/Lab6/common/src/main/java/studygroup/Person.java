package studygroup;

import java.time.LocalDateTime;

/**
 * Студент и его параметры.
 */
public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private LocalDateTime birthday = null; //Поле может быть null
    private Double height = null; //Поле может быть null, Значение поля должно быть больше 0
    private Country nationality = null; //Поле может быть null
    private Location location; //Поле не может быть null

    public Person(String name, LocalDateTime birthday, Double height, Country nationality, Location location) {
        this.name = name;
        this.birthday = birthday;
        this.height  = height;
        this.nationality = nationality;
        this.location = location;
    }

    /**
     * @return Возвращает имя.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Возвращает дату рожение.
     */
    public LocalDateTime getBirthday() {
        return birthday;
    }

    /**
     * @return Возвращает рост.
     */
    public Double getHeight() {
        return height;
    }

    /**
     * @return Возвращает родную страну.
     */
    public Country getNationality() {
        return nationality;
    }

    /**
     * @return Возвращает место дислокации.
     */
    public Location getLocation() {
        return location;
    }
}
