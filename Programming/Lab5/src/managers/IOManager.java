package managers;

import route.Route;

import java.util.ArrayList;

public interface IOManager {
    ArrayList<Route> read();
    void write();
}
