package route;

import exception.InvalidArgumentException;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Formatter;

/**
 * Класс, является полем класса Route
 * @author spynad
 * @version govno
 */
public class FirstLocation implements Serializable {
    private static final long serialVersionUID = -4314801651620839309L;

    @NotNull
    private Integer x; //Поле не может быть null

    private long y;

    @NotNull
    @Min(value = 1)
    private String name; //Строка не может быть пустой, Поле не может быть null

    public FirstLocation(Integer x, long y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
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


    @Override
    public String toString() {
        Formatter f = new Formatter();
        f.format("\"%d,%d,\"%s\"\"", x, y, name);
        return f.toString();
    }
}
