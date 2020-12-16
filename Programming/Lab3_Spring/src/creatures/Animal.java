package creatures;

import enums.Direction;

public class Animal extends MulticellularOrganism {
    Animal(String name) {
        super(name);
        setPartsLocation(true);
        setDirection(Direction.HORIZONTAL);
        setEvolutionStage(4);
    }

    @Override
    public String toString() {
        return "creatures.Animal. " + getName() + ". "
                + " enums.Origin: " + getOrigin().toString()
                + " Place of living: " + getPol().toString()
                + " enums.Direction: " + getDirection().toString();
    }
}
