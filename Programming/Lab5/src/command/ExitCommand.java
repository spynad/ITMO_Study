package command;

/**
 * Класс-команда, реализующая выход из JVM
 */
public class ExitCommand implements Command{

    public void execute() {
        System.exit(0);
    }
}
