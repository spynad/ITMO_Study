package main;

public class Lab5 {

    public static void main(String[] args) {
        if (args.length > 0) {
            if(!args[0].equals("")) {
                Application application = new Application();
                application.init(args[0]);
                application.loop();
            } else {
                System.err.println("Usage: program_name fileName");
            }
        }
        else {
            System.err.println("Usage: program_name fileName");
        }
    }
}
