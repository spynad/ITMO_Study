package creatures;

public final class UnicellularOrganism extends Organism {
    public UnicellularOrganism(String name) {
        super(name);
        setEvolutionStage(1);
    }

    @Override
    public String toString() {
        return "Unicellular creatures.Organism. " + getName() + ". "
                + " enums.Origin: " + getOrigin().toString()
                + " Place of living: " + getPol().toString();
    }
}
