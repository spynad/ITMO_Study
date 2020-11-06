import java.util.Objects;

public abstract class Organism {
    private String name;
    private Origin origin = Origin.UNKNOWN;
    private PlaceOfLiving pol = PlaceOfLiving.UNKNOWN;
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

    public boolean getPartsLocation() {
        return partsLocation;
    }

    public int getEvolutionStage() {
        return EvolutionStage;
    }

    public void setPartsLocation(boolean p) {
        partsLocation = p;
    }

    protected void setEvolutionStage(int evolutionStage) {
        EvolutionStage = evolutionStage;
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
