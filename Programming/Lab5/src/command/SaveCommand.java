package command;

import managers.IFileManager;

/**
 * Класс-команда, реализующая сохранение коллекции в файл
 */
public class SaveCommand implements Command{
    IFileManager fileManager;

    SaveCommand(IFileManager fileManager) {
        this.fileManager = fileManager;
    }
    public void execute() {
        fileManager.writeFile();
    }
}
