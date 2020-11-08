import java.util.Objects;

public class MulticellularOrganism extends Organism {
    private Direction direction = Direction.UNKNOWN;

    MulticellularOrganism(String name) {
        super(name);
        setEvolutionStage(2);
    }

    public Direction getDirection() {
        return direction;
    }

    protected void setDirection(Direction dir) {
        direction = dir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MulticellularOrganism that = (MulticellularOrganism) o;
        return direction == that.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), direction);
    }

    @Override
    public String toString() {
        return "Multicellular Organism. " + getName() + ". " + "Direction: " + direction.toString();
    }
}
