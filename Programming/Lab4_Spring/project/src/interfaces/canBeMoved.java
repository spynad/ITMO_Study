package interfaces;

import enums.Location;

public interface canBeMoved {
    void move(Location location);
    Location getLocation();
}
