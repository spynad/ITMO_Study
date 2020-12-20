package creatures;

import java.util.Objects;

import enums.Family;
import enums.Origin;
import enums.PlaceOfLiving;

public class AncientStar extends Organism{
    private boolean isTropical;
    private boolean modernity;
    private boolean fantasticalTransformed;

    public AncientStar(String name, boolean isTropical, Origin origin, PlaceOfLiving pol, boolean modernity) {
        super(name, origin, pol);
        this.isTropical = isTropical;
        this.modernity = modernity;
        setEvolutionStage(5);
        setFamily(Family.ECHINODERM);
    }

    AncientStar(String name) {
        super(name);
    }

    public void setFantasticalTransformed(Boolean bool) {
        fantasticalTransformed = bool;
    }


    public boolean isTropical() {
        return isTropical;
    }

    public boolean isModernity() {
        return modernity;
    }

    public boolean isFantasticalTransformed() {
        return fantasticalTransformed;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AncientStar that = (AncientStar) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "AncientStar. " + getName() + '.';
    }
}