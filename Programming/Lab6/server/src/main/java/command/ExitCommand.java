package command;

import commands.AbstractCommand;
import commands.Command;
import server.Application;

/**
 * Класс-команда, реализующая выход из JVM
 */
public class ExitCommand extends AbstractCommand implements Command {

    public ExitCommand(boolean req){
        super(req);
    }

    //TODO: сделать не через статическое поле
    public void execute() {
        Application.setIsRunning(false);
    }
}
