package exceptions;

public class InvalidExperienceException extends IllegalArgumentException  {
    private final int exp;

    public InvalidExperienceException(String message, int experience) {
        super(message);
        exp = experience;
    }

    public int getExp() {
        return exp;
    }

}