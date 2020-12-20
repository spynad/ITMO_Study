package exceptions;

public class NotEnoughPeopleException extends Exception{
    private final int humanCount;

    public NotEnoughPeopleException(String message, int humanCount) {
        super(message);
        this.humanCount = humanCount;

    }

    public int getExp() {
        return humanCount;
    }
}
