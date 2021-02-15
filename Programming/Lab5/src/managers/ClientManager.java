package managers;

import command.CommandInvoker;

import java.util.Scanner;

public class ClientManager {
    CommandInvoker commandInvoker;

    public ClientManager(IRouteManager routeManager, IFileManager fileManager) {
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
