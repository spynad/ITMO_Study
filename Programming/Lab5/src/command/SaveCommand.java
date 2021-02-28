package command;

import main.RouteWriter;

/**
 * Класс-команда, реализующая сохранение коллекции в файл
 */
public class SaveCommand implements Command{
    RouteWriter fileManager;

    SaveCommand(RouteWriter fileManager) {
        this.fileManager = fileManager;
    }
    public void execute() {
        fileManager.write();
    }
}
