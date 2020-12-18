package places;

import java.util.Objects;

public class Lab {
    private boolean isFake;

    Lab(boolean isFake) {
        this.isFake = isFake;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lab lab = (Lab) o;
        return isFake == lab.isFake;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isFake);
    }

    @Override
    public String toString() {
        if (isFake)
            return "Fake lab";
        else
            return "Lab";
    }
}