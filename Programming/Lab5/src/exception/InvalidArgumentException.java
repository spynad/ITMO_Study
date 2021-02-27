package exception;

/**
 * Класс-исключение, которое выбрасывается, если какой-либо из аргументов чего-либо не прошел валидацию
 * @author spynad
 * @version govno
 */
public class InvalidArgumentException extends Exception {
    public InvalidArgumentException(String message) {
        super(message);
    }
}
