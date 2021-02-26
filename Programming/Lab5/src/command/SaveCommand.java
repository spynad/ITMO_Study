package command;

import managers.IOManager;

/**
 * Класс-команда, реализующая сохранение коллекции в файл
 */
public class SaveCommand implements Command{
    IOManager fileManager;

    SaveCommand(IOManager fileManager) {
        this.fileManager = fileManager;
    }
    public void execute() {
        fileManager.write();
    }
}
