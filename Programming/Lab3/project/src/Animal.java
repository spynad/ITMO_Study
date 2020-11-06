public class Animal extends MulticellularOrganism {
    Animal(String name) {
        super(name);
        setPartsLocation(true);
        setDirection(Direction.HORIZONTAL);
        setEvolutionStage(4);
    }

    @Override
    public String toString() {
        return getName() + " is "
                + "Origin: " + getOrigin().toString()
                + "Place of living: " + getPol().toString()
                + "Direction: " + getDirection().toString();
    }
}
