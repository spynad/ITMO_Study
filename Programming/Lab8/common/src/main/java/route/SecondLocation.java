package route;

import exception.InvalidArgumentException;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Formatter;
import java.util.Locale;

/**
 * Класс, является полем класса Route
 * @author spynad
 * @version govno
 */
public class SecondLocation implements Serializable {
    private static final long serialVersionUID = -6047800813680293373L;

    @NotNull
    private Integer x; //Поле не может быть null

    @NotNull
    private Long y; //Поле не может быть null

    @NotNull
    private Double z; //Поле не может быть null

    public SecondLocation(Integer x, Long y, Double z) {
        this.x = x;
        this.y = y;
        this.z = z;
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

    @Override
    public String toString() {
        Formatter f = new Formatter();
        f.format("\"%d,%d,%s\"", x, y, String.format(Locale.ROOT, "%.2f", z));
        return f.toString();
    }
}
