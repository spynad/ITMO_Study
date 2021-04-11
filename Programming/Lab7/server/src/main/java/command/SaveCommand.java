package command;

import file.RouteWriter;

/**
 * Класс-команда, реализующая сохранение коллекции в файл
 */
public class SaveCommand implements ServerCommand{
    private final RouteWriter routeWriter;

    public SaveCommand(RouteWriter routeWriter) {
        this.routeWriter = routeWriter;
    }
    public void execute() {
        routeWriter.write();
    }
}
