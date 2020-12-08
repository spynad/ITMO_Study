import enums.PlaceOfLiving;

import java.util.ArrayList;
import java.util.Objects;

public final class Elder extends MulticellularOrganism{
    private boolean corruptState;
    private ArrayList<Parts> parts;

    Elder(String name, ArrayList<Parts> parts) {
        super(name);
        this.parts = parts;
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

    public void addPart(Parts parts) {
        this.parts.add(parts);
    }

    public void use(String strPart, String mode) {
        for (Parts obj : parts) {
            if (strPart.equals(obj.getName())) {
                obj.use(getName(), mode);
            }
        }
    }

    public void use(String strPart) {
        for (Parts obj : parts) {
            if (strPart.equals(obj.getName())) {
                obj.use(getName());
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Elder elder = (Elder) o;
        return corruptState == elder.corruptState &&
                Objects.equals(parts, elder.parts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(corruptState, parts);
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
