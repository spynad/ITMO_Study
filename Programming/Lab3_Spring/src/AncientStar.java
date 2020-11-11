import java.util.Objects;

public class AncientStar {
    private String name;
    private boolean isTropical;
    private Origin origin = Origin.UNKNOWN;
    private boolean modernity;
    private boolean fantasticalTransformed;

    AncientStar(String name, boolean isTropical, Origin origin, boolean modernity) {
        this.name = name;
        this.isTropical = isTropical;
        this.origin = origin;
        this.modernity = modernity;
    }

    AncientStar(String name) {
        this.name = name;
    }

    public void setFantasticalTransformed(Boolean bool) {
        fantasticalTransformed = bool;
    }

    public String getName() {
        return name;
    }

    public boolean isTropical() {
        return isTropical;
    }

    public boolean isModernity() {
        return modernity;
    }

    public boolean isFantasticalTransformed() {
        return fantasticalTransformed;
    }

    public Origin getOrigin() {
        return origin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AncientStar that = (AncientStar) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "AncientStar. " + name + '.';
    }
}
