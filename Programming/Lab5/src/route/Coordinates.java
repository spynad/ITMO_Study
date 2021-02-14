package route;

import route.exceptions.InvalidArgumentException;

import java.util.Formatter;
import java.util.Locale;

public class Coordinates {
    private long x; //Значение поля должно быть больше -776
    private Double y; //Поле не может быть null

    //TODO: объединить валидацию полей класса в один метод, так должно быть красивее
    public Coordinates(long x, Double y) throws InvalidArgumentException {
        this.x = x;
        this.y = y;
        if (!(validateX() && validateY()))
            throw new InvalidArgumentException();
    }

    public long getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public boolean validateX() {
        return x > -776;
    }

    public boolean validateY() {
        return y != null;
    }

    @Override
    public String toString() {
        Formatter f = new Formatter();
        f.format("\"%d,%s\"", x, String.format(Locale.ROOT, "%.2f", y));
        return f.toString();
    }
}
