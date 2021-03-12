package commands;

import route.Route;

public interface RouteCommand extends Command{
    void execute(Route route);
}
