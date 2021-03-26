package route;

import exception.InvalidArgumentException;

import java.io.Serializable;
import java.util.Formatter;
import java.util.Locale;

/**
 * Класс, является полем класса Route
 * @author spynad
 * @version govno
 */
public class Coordinates implements Serializable {
    private static final long serialVersionUID = 1278816392291719517L;

    private long x; //Значение поля должно быть больше -776
    private Double y; //Поле не может быть null

    public Coordinates(long x, Double y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    @Override
    public String toString() {
        Formatter f = new Formatter();
        f.format("\"%d,%s\"", x, String.format(Locale.ROOT, "%.2f", y));
        return f.toString();
    }
}
