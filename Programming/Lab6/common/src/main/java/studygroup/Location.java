package studygroup;

/**
 * Координаты и название  локации.
 */
public class Location {
    private int x;
    private long y;
    private long z;
    private String name; //Строка не может быть пустой, Поле не может быть null

    public Location(int x, long y, long z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    /**
     * @return x координата.
     */
    public int getX() {
        return x;
    }

    /**
     * @return y координата.
     */
    public long getY() {
        return y;
    }

    /**
     * @return z координата.
     */
    public long getZ() {
        return z;
    }

    /**
     * @return название локации.
     */
    public String getName() {
        return name;
    }
}
