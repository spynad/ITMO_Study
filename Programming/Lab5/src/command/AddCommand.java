package command;

import managers.IRouteManager;
import route.Coordinates;
import route.FirstLocation;
import route.SecondLocation;
import route.exceptions.InvalidArgumentException;

import java.util.Scanner;

public class AddCommand implements Command{
    IRouteManager routeManager;

    AddCommand(IRouteManager routeManager) {
        this.routeManager = routeManager;
    }

    //TODO: это пиздец x2
    public void execute() {
        routeManager.readRoute();
    }
}
