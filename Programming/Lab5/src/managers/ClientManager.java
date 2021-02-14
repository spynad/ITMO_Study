package managers;

import command.CommandInvoker;

import java.util.Scanner;

public class ClientManager {
    IRouteManager routeManager;
    CommandInvoker commandInvoker;

    public ClientManager(IRouteManager routeManager) {
        this.routeManager = routeManager;
        commandInvoker = new CommandInvoker(routeManager);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                System.out.print("> ");
                String str = scanner.nextLine();
                commandInvoker.execute(str);
            } catch (IllegalStateException ise) {

            }

        }
    }
}
