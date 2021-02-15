package route;

import route.exceptions.InvalidArgumentException;

import java.util.Formatter;
import java.util.Locale;

public class SecondLocation {
    private Integer x; //Поле не может быть null
    private Long y; //Поле не может быть null
    private Double z; //Поле не может быть null

    public SecondLocation(Integer x, Long y, Double z) throws InvalidArgumentException{
        this.x = x;
        this.y = y;
        this.z = z;
        if (!(validateX() && validateY() && validateZ()))
            throw new InvalidArgumentException();
    }

    public Integer getX() {
        return x;
    }

    public Long getY() {
        return y;
    }

    public Double getZ() {
        return z;
    }

    public boolean validateX() {
        return x != null;
    }

    public boolean validateY() {
        return y != null;
    }

    public boolean validateZ() {
        return z != null;
    }

    @Override
    public String toString() {
        Formatter f = new Formatter();
        f.format("\"%d,%d,%s\"", x, y, String.format(Locale.ROOT, "%.2f", z));
        return f.toString();
    }
}
