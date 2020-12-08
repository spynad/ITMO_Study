import enums.Direction;

public final class Plant extends MulticellularOrganism {
    Plant(String name) {
        super(name);
        setDirection(Direction.VERTICAL);
        setEvolutionStage(3);
    }

    @Override
    public String toString() {
        return "Plant. " + getName() + ". "
                + " enums.Origin: " + getOrigin().toString()
                + " Place of living: " + getPol().toString()
                + " enums.Direction: " + getDirection().toString();
    }
}
