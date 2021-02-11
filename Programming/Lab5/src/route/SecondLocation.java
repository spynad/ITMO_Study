package route;

import route.exceptions.InvalidArgumentException;

import java.util.Formatter;

public class SecondLocation {
    private Integer x; //Поле не может быть null
    private Long y; //Поле не может быть null
    private Double z; //Поле не может быть null

    //TODO: объединить валидацию полей класса в один метод, так должно быть красивее
    public SecondLocation(Integer x, Long y, Double z) throws InvalidArgumentException{
        this.x = x;
        this.y = y;
        this.z = z;
        if (!(validateX() && validateY() && validateZ()))
            throw new InvalidArgumentException();
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
        f.format("\"%d,%d,%f\"", x, y, z);
        return f.toString();
    }
}
