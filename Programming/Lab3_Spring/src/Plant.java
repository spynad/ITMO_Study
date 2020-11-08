public final class Plant extends MulticellularOrganism {
    Plant(String name) {
        super(name);
        setDirection(Direction.VERTICAL);
        setEvolutionStage(3);
    }

    @Override
    public String toString() {
        return "Plant. " + getName() + ". "
                + " Origin: " + getOrigin().toString()
                + " Place of living: " + getPol().toString()
                + " Direction: " + getDirection().toString();
    }
}
