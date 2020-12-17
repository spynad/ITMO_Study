package items;

public final class StudyMaterial {
    private final String type;
    private final int expAmount;

    public StudyMaterial(int amount) {
        expAmount = amount;

        if (expAmount > 500) {
            type = "Big Study Material";
        }
        else if (expAmount > 250) {
            type = "Medium Study Material";
        }
        else {
            type = "Small Study Material";
        }
    }

    public int getExpAmount() {
        return expAmount;
    }

    public String getType() {
        return type;
    }
}
