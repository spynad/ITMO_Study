package places;

import java.util.Objects;

public class Cave {
    private boolean canBeEntered;

    public boolean isCanBeEntered() {
        return canBeEntered;
    }

    public void setCanBeEntered(boolean canBeEntered) {
        this.canBeEntered = canBeEntered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cave cave = (Cave) o;
        return isCanBeEntered() == cave.isCanBeEntered();
    }

    @Override
    public int hashCode() {
        return Objects.hash(isCanBeEntered());
    }

    @Override
    public String toString() {
        return "Cave{" +
                "canBeEntered=" + canBeEntered +
                '}';
    }
}
