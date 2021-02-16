package route;

import route.exceptions.InvalidArgumentException;

import java.util.Formatter;

/**
 * Класс, является полем класса Route
 * @author spynad
 * @version govno
 */
public class FirstLocation {
    private Integer x; //Поле не может быть null
    private long y;
    private String name; //Строка не может быть пустой, Поле не может быть null

    //TODO: объединить валидацию полей класса в один метод, так должно быть красивее
    public FirstLocation(Integer x, long y, String name) throws InvalidArgumentException{
        this.x = x;
        this.y = y;
        this.name = name;
        if (!(validateX() && validateName()))
            throw new InvalidArgumentException("invalid firstlocation");
    }

    public Integer getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public boolean validateX() {
        return x != null;
    }

    public boolean validateName() {
        return !name.equals("");
    }

    @Override
    public String toString() {
        Formatter f = new Formatter();
        f.format("\"%d,%d,\"%s\"\"", x, y, name);
        return f.toString();
    }
}
