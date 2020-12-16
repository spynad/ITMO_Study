package creatures;

import java.util.Objects;
import creatures.parts.*;
import enums.*;

public final class Elder extends MulticellularOrganism{
    private boolean corruptState;
    private Parts wing;

    public Elder(String name, Parts wing) {
        super(name);
        this.wing = wing;
        setPartsLocation(true);
        setEvolutionStage(0);
        changePol(PlaceOfLiving.ANTARCTICA);
    }

    public boolean isCorruptState() {
        return corruptState;
    }

    public void setCorruptState(boolean corrupt) {
        corruptState = corrupt;
        System.out.println(getName() + " is now corrupted.");
    }

    public void fly() {
        wing.use("fly");
    }

    public void swim(){
        wing.use("swim");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Elder elder = (Elder) o;
        return corruptState == elder.corruptState &&
                Objects.equals(wing, elder.wing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(corruptState, wing);
    }

    @Override
    public String toString() {
        String corrupt;
        if(isCorruptState())
            corrupt = "corrupted";
        else
            corrupt = "uncorrupted";
        return getName() + " is " + corrupt + " "
                + " enums.Origin: " + getOrigin().toString()
                + " Place of living: " + getPol().toString()
                + " enums.Direction: " + getDirection().toString();
    }
}
