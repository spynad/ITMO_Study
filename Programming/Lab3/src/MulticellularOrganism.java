public class MulticellularOrganism extends Organism {
    private Direction direction;

    MulticellularOrganism(String name) {
        super(name);
    }

    public Direction getDirection() {
        return direction;
    }

    protected void setDirection(Direction dir) {
        direction = dir;
    }
}
