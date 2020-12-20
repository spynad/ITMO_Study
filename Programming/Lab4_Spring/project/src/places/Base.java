package places;

import enums.Location;

public class Base {
    private boolean isConnectionEstablished;
    private final Location loc = Location.BASE;

    public void connect() {
        if (isConnectionEstablished) {
            System.out.println("Connection to the base already established.");
        } else {
            while(!isConnectionEstablished) {
                System.out.println("Trying to connect to base...");
                if (Math.random() > 0.6) {
                    System.out.println("Connected to the base!");
                    isConnectionEstablished = true;
                }
            }
        }
    }


    public void acceptMessage(String message) {
        if(isConnectionEstablished) {
            System.out.println("Got message: " + message);
        }
    }

    public boolean isConnectionEstablished() {
        return isConnectionEstablished;
    }
}
