package server;

import log.Log;

public class Main {
    public static void main(String[] args) {
        if (args.length == 2) {
            Application application = new Application();
            try {
                application.start(args[0], Integer.parseInt(args[1]));
            } catch (NumberFormatException e) {
                Log.getLogger().error("Invalid port");
            }
        } else {
            Log.getLogger().error("Expected arguments: port file_name");
        }
    }
}
