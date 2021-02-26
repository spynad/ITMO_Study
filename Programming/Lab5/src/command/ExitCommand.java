package command;

import main.Application;

/**
 * Класс-команда, реализующая выход из JVM
 */
public class ExitCommand implements Command{

    ExitCommand (){
    }

    public void execute() {
        Application.setIsRunning(false);
    }
}
