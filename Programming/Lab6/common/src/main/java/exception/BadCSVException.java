package exception;

/**
 * Класс-исключение, которое выбрасывается, если что-то произошло при парсинге csv-файла
 * @author spynad
 * @version govno
 */
public class BadCSVException extends RuntimeException {
    public BadCSVException(String message) {
        super(message);
    }
}
