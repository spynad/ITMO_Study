package creatures;

import enums.Direction;
import interfaces.canSleep;

public class Animal extends MulticellularOrganism implements canSleep {
    Animal(String name) {
        super(name);
        setPartsLocation(true);
        setDirection(Direction.HORIZONTAL);
        setEvolutionStage(4);
    }

    public void sleep() {
        System.out.println(getName() + " is sleeping");
    }

    @Override
    public String toString() {
        return "Animal. " + getName() + ". "
                + " Origin: " + getOrigin().toString()
                + " Place of living: " + getPol().toString()
                + " Direction: " + getDirection().toString();
    }
}
