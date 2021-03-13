package command;

import file.RouteWriter;

/**
 * Класс-команда, реализующая сохранение коллекции в файл
 */
public class SaveCommand extends AbstractServerCommand implements ServerCommand{
    RouteWriter routeWriter;

    SaveCommand(RouteWriter routeWriter) {
        this.routeWriter = routeWriter;
    }
    public void execute() {
        routeWriter.write();
    }
}
