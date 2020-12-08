public final class UnicellularOrganism extends Organism {
    UnicellularOrganism(String name) {
        super(name);
        setEvolutionStage(1);
    }

    @Override
    public String toString() {
        return "Unicellular Organism. " + getName() + ". "
                + " enums.Origin: " + getOrigin().toString()
                + " Place of living: " + getPol().toString();
    }
}
