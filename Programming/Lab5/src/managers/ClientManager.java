package managers;

import command.CommandInvoker;

import java.util.Scanner;

public class ClientManager {
    IRouteManager routeManager;
    IFileManager fileManager;
    CommandInvoker commandInvoker;

    public ClientManager(IRouteManager routeManager, IFileManager fileManager) {
        this.routeManager = routeManager;
        this.fileManager = fileManager;
        commandInvoker = new CommandInvoker(routeManager, fileManager);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                System.out.print("> ");
                String str = scanner.nextLine();
                commandInvoker.execute(str);
            } catch (IllegalStateException ise) {
                System.err.println(ise.getMessage());
            }

        }
    }
}
