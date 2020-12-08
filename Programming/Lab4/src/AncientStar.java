import enums.Origin;
import enums.PlaceOfLiving;

import java.util.Objects;

public class AncientStar extends Organism {
    private boolean isTropical;
    private boolean modernity;
    private boolean fantasticalTransformed;
    private final int evolutionStage = 5;
    private boolean hasRedundantPropeties = true;

    AncientStar(String name, boolean isTropical, Origin origin, boolean modernity) {
        super(name, origin, PlaceOfLiving.UNKNOWN);
        this.isTropical = isTropical;
        this.modernity = modernity;
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
        return "Ancients, those who allegedly gave birth to life on Earth, either for fun or by mistake.";
    }
}