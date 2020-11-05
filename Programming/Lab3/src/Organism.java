public abstract class Organism {
    private String name;
    private Origin origin;
    private PlaceOfLiving pol;
    private boolean partsLocation;


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

    public void setPartsLocation(boolean p) {
        partsLocation = p;
    }

    public void changeOrigin(Origin o) {
        origin = o;
        System.out.println(name + "'s origin is now " + origin.toString());
    }

    public void changePol(PlaceOfLiving pol) {
        this.pol = pol;
        System.out.println("Organism's place of living is now " + this.pol.toString());
    }


}
