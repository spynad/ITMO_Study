package managers;

import log.Log;
import route.Coordinates;
import route.FirstLocation;
import route.Route;
import route.SecondLocation;
import route.exceptions.InvalidArgumentException;

import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;

public class RouteManager implements IRouteManager{
    private final Stack<Route> routes;
    private final LocalDate dateOfInit = LocalDate.now();

    public RouteManager() {
        Log.logger.log(Level.INFO,"RouteManager init");
        routes = new Stack<>();
    }

    public void addRoute(String name, Coordinates coordinates, FirstLocation from, SecondLocation to, double distance) throws InvalidArgumentException {
        Route route = new Route(name, coordinates, from, to, distance);
        routes.add(route);
    }

    public void addRoutes(ArrayList<Route> routes) {
        Log.logger.log(Level.INFO,"Adding routes into stack");
        for (Route route : routes) {
            this.routes.push(route);
        }
    }

    public Stack<Route> getRoutes() {
        return routes;
    }

    public void info() {
        System.out.printf("%s, Date of init:%s, Number of objects: %d\n", routes.getClass().getName(),
                dateOfInit.toString(), routes.size());
    }

    public void clear() {
        routes.removeAllElements();
    }

    public void removeAllByDistance(double distance) {
        routes.removeIf(route -> distance == route.getDistance());
    }

    public void sumOfDistance() {
        double sum = 0;
        for (Route route : routes) {
            sum += route.getDistance();
        }
        System.out.printf("Sum of distance: %.2f\n", sum);
    }

    public void show() {
        for (Route route: routes) {
            showElement(route);
        }
    }

    public void showElement(Route route) {
        System.out.printf("Route object:ID:%d, Name:%s, Coordinates: {x:%d, y:%.2f}, Creation date: %s" +
                        " from: {x:%d, y:%d, name:%s), to: {x:%d, y:%d, z:%.2f}, Distance: %.2f\n", route.getId(),
                route.getName(), route.getCoordinates().getX(), route.getCoordinates().getY(),
                route.getCreationDate().toString(), route.getFrom().getX(), route.getFrom().getY(),
                route.getFrom().getName(), route.getTo().getX(), route.getTo().getY(),
                route.getTo().getZ(), route.getDistance());
    }

    public void removeFirst() {
        if (routes.size() == 0)
            throw new EmptyStackException();
        routes.pop();
    }

    public void removeById(int id) {
        int count = 0;
        Iterator<Route> iterator = routes.iterator();
        while(iterator.hasNext()) {
            Route route = iterator.next();
            if (route.getId() == id) {
                iterator.remove();
                count++;
            }
        }
        System.out.printf("Removed %d object(-s)\n", count);
    }

    public void removeAt(int index) {
        try {
            routes.remove(index);
            System.out.println("Removed 1 object");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("tried to remove non-existent element");
        }

    }

    public void filterContainsName(String name) {
        for (Route route : routes) {
            if (route.getName().contains(name)) {
                showElement(route);
            }
        }
    }

    public void readRoute() {
        Scanner scanner = new Scanner(System.in);
        String name, nameFrom;
        long x, yFrom, yTo;
        int xFrom, xTo;
        double y, zTo, distance;
        Coordinates coordinates;
        FirstLocation firstLocation;
        SecondLocation secondLocation;

        while(true) {
            try {
                System.out.println("enter name: ");
                name = scanner.nextLine();
                break;
            } catch (NumberFormatException nfe) {
                System.err.println("invalid argument");
            }
        }

        while(true) {
            try {
                System.out.println("enter coordinates.x: ");
                x = Long.parseLong(scanner.nextLine());
                break;
            } catch (NumberFormatException nfe) {
                System.err.println("invalid argument");
            }
        }

        while(true) {
            try {
                System.out.println("enter coordinates.y: ");
                y = Double.parseDouble(scanner.nextLine());
                coordinates = new Coordinates(x, y);
                break;
            } catch (NumberFormatException | InvalidArgumentException nfe) {
                System.err.println("invalid argument");
            }
        }

        while(true) {
            try {
                System.out.println("enter from.x: ");
                xFrom = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException nfe) {
                System.err.println("invalid argument");
            }
        }

        while(true) {
            try {
                System.out.println("enter from.y: ");
                yFrom = Long.parseLong(scanner.nextLine());
                break;
            } catch (NumberFormatException nfe) {
                System.err.println("invalid argument");
            }
        }

        while(true) {
            try {
                System.out.println("enter from.name: ");
                nameFrom = scanner.nextLine();
                firstLocation = new FirstLocation(xFrom, yFrom, nameFrom);
                break;
            } catch (NumberFormatException | InvalidArgumentException nfe) {
                System.err.println("invalid argument");
            }
        }

        while(true) {
            try {
                System.out.println("enter to.x: ");
                xTo = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException nfe) {
                System.err.println("invalid argument");
            }
        }

        while(true) {
            try {
                System.out.println("enter to.y: ");
                yTo = Long.parseLong(scanner.nextLine());
                break;
            } catch (NumberFormatException nfe) {
                System.err.println("invalid argument");
            }
        }

        while(true) {
            try {
                System.out.println("enter to.z: ");
                zTo = Double.parseDouble(scanner.nextLine());
                secondLocation = new SecondLocation(xTo, yTo, zTo);
                break;
            } catch (NumberFormatException | InvalidArgumentException nfe) {
                System.err.println("invalid argument");
            }
        }

        while(true) {
            try {
                System.out.println("enter distance: ");
                distance = Double.parseDouble(scanner.nextLine());
                addRoute(name, coordinates, firstLocation, secondLocation, distance);
                break;
            } catch (NumberFormatException nfe) {
                System.err.println("invalid argument");
            } catch (InvalidArgumentException iae) {
                System.err.println("invalid arguments");
            }
        }
    }
}
