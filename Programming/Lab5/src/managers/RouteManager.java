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

    public void addRoute(String name, Coordinates coordinates, FirstLocation from, SecondLocation to, double distance)
            throws InvalidArgumentException, NumberFormatException {
        Route route = new Route(name, coordinates, from, to, distance);
        routes.add(route);
    }

    public void addRoute(Route route) {
        routes.add(route);
    }

    public void addRoutes(ArrayList<Route> routes) {
        Log.logger.log(Level.INFO,"Adding routes into stack");
        for (Route route : routes) {
            this.routes.push(route);
        }
    }

    public int findRouteById(int id) {
        int index = 0;
        for (Route route : routes) {
            if (route.getId() == id) {
                return index;
            }
            index++;
        }
        return 0;
    }

    public Stack<Route> getRoutes() {
        return routes;
    }

    public void info() {
        System.out.printf("%s, Date of init:%s, Number of objects: %d%n", routes.getClass().getName(),
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
        System.out.printf("Sum of distance: %.2f%n", sum);
    }

    public void show() {
        for (Route route: routes) {
            showElement(route);
        }
    }

    public void showElement(Route route) {
        String from;
        if (route.getFrom() == null) {
            from = "null";
        } else {
            from = String.format("{x:%d, y:%d, name:%s}", route.getFrom().getX(), route.getFrom().getY(), route.getFrom().getName());
        }
        System.out.printf("Route object:ID:%d, Name:%s, Coordinates: {x:%d, y:%.2f}, Creation date: %s" +
                        " from: %s, to: {x:%d, y:%d, z:%.2f}, Distance: %.2f%n", route.getId(),
                route.getName(), route.getCoordinates().getX(), route.getCoordinates().getY(),
                route.getCreationDate().toString(), from, route.getTo().getX(), route.getTo().getY(),
                route.getTo().getZ(), route.getDistance());
    }

    public void removeFirst() {
        try {
            routes.pop();
        } catch (EmptyStackException ese) {
            System.err.println("The stack is empty");
        }

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
        System.out.printf("removed %d object(-s)%n", count);
    }

    public void removeAt(int index) {
        try {
            routes.remove(index);
            System.out.println("removed 1 object");
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

    public Route readRoute(String nameArg, String distanceArg, int id) throws InvalidArgumentException{
        DataReader dataReader = new DataReader();
        Coordinates coordinates;
        FirstLocation firstLocation;
        SecondLocation secondLocation;
        coordinates = dataReader.readCoordinates();
        firstLocation = dataReader.readFirstLocation();
        secondLocation = dataReader.readSecondLocation();
        if (id != -1) {
            return new Route(id, nameArg, coordinates, firstLocation, secondLocation, Double.parseDouble(distanceArg));
        } else {
            return new Route(nameArg, coordinates, firstLocation, secondLocation, Double.parseDouble(distanceArg));
        }

        //addRoute(nameArg, coordinates, firstLocation, secondLocation, Double.parseDouble(distanceArg));
    }

    public void updateId(int id, String name, Coordinates coordinates, FirstLocation from, SecondLocation to, double distance) {
        try {
            int index = findRouteById(id);
            Route route = new Route(name, coordinates, from, to, distance);
            routes.set(index, route);
        } catch (InvalidArgumentException e) {
            System.err.println("invalid arguments");
        }
    }

    public void updateId(int id, Route route) {
        try {
            int index = findRouteById(id);
            routes.set(index, route);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("unknown element");
        }
    }

    public static class DataReader {
        Scanner scanner = new Scanner(System.in);

        public Coordinates readCoordinates() {
            while(true) {
                try {
                    System.out.println("enter coordinates fields(x, y): ");
                    String readStr = scanner.nextLine();
                    String[] splitStr = readStr.split("\\s*,\\s*");
                    if (splitStr.length != 2)
                        throw new InvalidArgumentException();
                    return new Coordinates(Long.parseLong(splitStr[0]), Double.parseDouble(splitStr[1]));
                } catch (InvalidArgumentException e) {
                    System.err.println("invalid arguments");
                }
            }
        }

        public FirstLocation readFirstLocation() {
            while(true) {
                try {
                    System.out.println("enter first location(x, y, name): ");
                    String readStr = scanner.nextLine();
                    if (readStr.equals("")) {
                        return null;
                    }
                    String[] splitStr = readStr.split("\\s*,\\s*");
                    if (splitStr.length != 3)
                        throw new InvalidArgumentException();
                    return new FirstLocation(Integer.parseInt(splitStr[0]),
                            Long.parseLong(splitStr[1]),
                            splitStr[2]);
                } catch (InvalidArgumentException e) {
                    System.err.println("invalid arguments");
                }
            }
        }

        public SecondLocation readSecondLocation() {
            while(true) {
                try {
                    System.out.println("enter second location(x, y, z): ");
                    String readStr = scanner.nextLine();
                    String[] splitStr = readStr.split("\\s*,\\s*");
                    if (splitStr.length != 3)
                        throw new InvalidArgumentException();
                    return new SecondLocation(Integer.parseInt(splitStr[0]),
                            Long.parseLong(splitStr[1]),
                            Double.parseDouble(splitStr[2]));
                } catch (InvalidArgumentException e) {
                    System.err.println("invalid arguments");
                }
            }
        }

        public int readInteger() {
            int value;
            while(true) {
                try {
                    value = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException nfe) {
                    System.err.println("invalid integer argument, try again");
                }
            }
            return value;
        }

        public long readLong() {
            long value;
            while(true) {
                try {
                    value = Long.parseLong(scanner.nextLine());
                    break;
                } catch (NumberFormatException nfe) {
                    System.err.println("invalid long argument, try again");
                }
            }
            return value;
        }

        public double readDouble() {
            double value;
            while(true) {
                try {
                    value = Double.parseDouble(scanner.nextLine());
                    break;
                } catch (NumberFormatException nfe) {
                    System.err.println("invalid double argument, try again");
                }
            }
            return value;
        }

        public String readString() {
            String str;
            str = scanner.nextLine();
            return str;
        }
    }
}
