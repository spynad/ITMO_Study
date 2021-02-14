package command;

import managers.IFileManager;
import managers.IRouteManager;

public class SaveCommand implements Command{
    IFileManager fileManager;

    SaveCommand(IFileManager fileManager) {
        this.fileManager = fileManager;
    }
    public void execute() {
        fileManager.writeFile();
    }
}
