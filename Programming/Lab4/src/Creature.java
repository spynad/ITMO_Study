public final class Creature extends MulticellularOrganism {
    private boolean isFromPrehistoricFolk;

    Creature(String name) {
        super(name);
    }

    public boolean isFromPrehistoricFolk() {
        return isFromPrehistoricFolk;
    }

    public void setFromPrehistoricFolk(boolean fromPrehistoricFolk) {
        isFromPrehistoricFolk = fromPrehistoricFolk;
    }

    @Override
    public String toString() {
        return "Creature. " + getName() + ". " + "Is he from prehistoric folk?: " + isFromPrehistoricFolk;
    }
}
