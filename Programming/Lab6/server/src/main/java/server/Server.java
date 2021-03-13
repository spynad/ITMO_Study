package server;

import log.Log;

public class Server {
    public static void main(String[] args) {
        if (args.length == 2) {
            Log.getLogger().info("Hello, world!");
            Application application = new Application();
            try {
                application.start(Integer.parseInt(args[0]), args[1]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid port");
            }
        } else {
            System.err.println("Expected arguments: port file_name");
        }
    }
}
