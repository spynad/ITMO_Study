package command;

import commands.AbstractCommand;
import commands.Command;
import file.RouteWriter;
import server.Application;

/**
 * Класс-команда, реализующая выход из JVM
 */
public class ExitCommand extends AbstractServerCommand implements ServerCommand {
    RouteWriter routeWriter;

    public ExitCommand(RouteWriter routeWriter) {
        this.routeWriter = routeWriter;
    }

    //TODO: сделать не через статическое поле
    public void execute() {
        routeWriter.write();
        System.exit(0);
    }
}
