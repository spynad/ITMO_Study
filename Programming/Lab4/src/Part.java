import enums.PartLocation;

import java.util.Objects;

public abstract class Part {
    private String name;
    private PartLocation partLocation;

    Part(String name, PartLocation partLocation) {
        this.name = name;
        this.partLocation = partLocation;
    }

    public String getName() {
        return name;
    }

    public PartLocation getPartLocation() {
        return partLocation;
    }

    public void setPartLocation(PartLocation partLocation) {
        this.partLocation = partLocation;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return Objects.equals(name, part.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Part. " + getName() + ". ";
    }
}
