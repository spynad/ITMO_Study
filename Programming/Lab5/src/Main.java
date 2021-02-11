import managers.RouteManager;

public class Main {

    public static void main(String[] args) {
        if ((args.length > 0)) {
            if (!args[0].equals("")) {
                RouteManager routeManager = new RouteManager(args[0]);
            }
        }
    }
}
