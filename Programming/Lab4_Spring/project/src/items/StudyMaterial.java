package items;

import exceptions.InvalidExperienceException;
import java.util.Objects;

public final class StudyMaterial {
    private final String type;
    private final int expAmount;

    public StudyMaterial(int amount){

        if (amount > 500) {
            type = "Big Study Material";
        }
        else if (amount > 250) {
            type = "Medium Study Material";
        }
        else if (amount > 0){
            type = "Small Study Material";
        }
        else
        {
            throw new InvalidExperienceException("InvalidExperienceException: Experience cannot be negative ", amount);
        }

        expAmount = amount;
    }

    public int getExpAmount() {
        return expAmount;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyMaterial that = (StudyMaterial) o;
        return getExpAmount() == that.getExpAmount() && Objects.equals(getType(), that.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getExpAmount());
    }

    @Override
    public String toString() {
        return "StudyMaterial{" +
                "type='" + type + '\'' +
                ", expAmount=" + expAmount +
                '}';
    }
}

