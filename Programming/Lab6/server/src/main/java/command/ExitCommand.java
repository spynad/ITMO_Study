package command;

import file.RouteWriter;

/**
 * Класс-команда, реализующая выход из JVM
 */
public class ExitCommand implements ServerCommand {
    private final RouteWriter routeWriter;

    public ExitCommand(RouteWriter routeWriter) {
        this.routeWriter = routeWriter;
    }

    //TODO: сделать не через статическое поле
    public void execute() {
        routeWriter.write();
        System.exit(0);
    }
}
