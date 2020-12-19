package creatures;

import java.util.Objects;
import enums.*;
import interfaces.canBeAdded;
import interfaces.canBeMoved;


public abstract class Organism implements canBeMoved {
    private final String name;
    private Origin origin = Origin.UNKNOWN;
    private PlaceOfLiving pol = PlaceOfLiving.UNKNOWN;
    private Location loc = Location.SOMEWHERE;
    private Family family = Family.UNKNOWN;
    private boolean partsLocation;
    private int EvolutionStage;


    Organism(String name, Origin origin, PlaceOfLiving pol) {
        this.name = name;
        this.origin = origin;
        this.pol = pol;
    }

    Organism(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Origin getOrigin() {
        return origin;
    }

    public PlaceOfLiving getPol() {
        return pol;
    }

    public Family getFamily() {
        return family;
    }

    public boolean getPartsLocation() {
        return partsLocation;
    }

    public Location getLoc() {
        return loc;
    }

    public int getEvolutionStage() {
        return EvolutionStage;
    }

    @Override
    public Location getLocation() {
        return loc;
    }

    public void setPartsLocation(boolean p) {
        partsLocation = p;
    }

    protected void setFamily(Family family) {
        this.family = family;
    }

    protected void setEvolutionStage(int evolutionStage) {
        EvolutionStage = evolutionStage;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }


    public void changeOrigin(Origin o) {
        origin = o;
        System.out.println(name + "'s origin is now " + origin.toString());
    }

    public void changePol(PlaceOfLiving pol) {
        this.pol = pol;
        System.out.println(name + "'s place of living is now " + this.pol.toString());
    }

    @Override
    public void move(Location location) {
        for (int i = 0; i <loc.getStorage().size(); i++) {
            if(equals(loc.getStorage().get(i))) {
                loc.getStorage().remove(i);
            }
        }
        location.getStorage().add((canBeAdded) this);
        loc = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organism organism = (Organism) o;
        return partsLocation == organism.partsLocation &&
                EvolutionStage == organism.EvolutionStage &&
                Objects.equals(name, organism.name) &&
                origin == organism.origin &&
                pol == organism.pol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, origin, pol, partsLocation, EvolutionStage);
    }
}
