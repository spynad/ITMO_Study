package managers;

import log.Log;
import route.Coordinates;
import route.FirstLocation;
import route.Route;
import route.SecondLocation;
import route.exceptions.InvalidArgumentException;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;

/**
 * Класс, которой реализует взаимодействие с коллекцией типа Stack
 * @author spynad
 * @version govno
 */
public class RouteManager implements IRouteManager{

    /**
     * Собственно говоря, сама коллекция Stack объектов Route
     */
    private final Stack<Route> routes;

    /**
     * Дата иницзализации коллекции
     */
    private final LocalDate dateOfInit = LocalDate.now();

    /**
     * Конструктор, иницализирующий коллекцию Stack
     */
    public RouteManager() {
        Log.logger.log(Level.INFO,"RouteManager init");
        routes = new Stack<>();
    }

    /**
     * Метод, создающий и добавляющий элемент {@link Route} в коллекцию Stack.
     * @param name - поле name элемента Route
     * @param coordinates - поле coordinates элемента Route
     * @param from - поле from элемента Route
     * @param to - поле to элемента Route
     * @param distance - поле distance элемента Route
     * @throws InvalidArgumentException - выбрасывает, когда какое-то из полей не проходить валидацию
     * @throws NumberFormatException - -//-
     */
    public void addRoute(String name, Coordinates coordinates, FirstLocation from, SecondLocation to, double distance)
            throws InvalidArgumentException, NumberFormatException {
        Route route = new Route(name, coordinates, from, to, distance);
        routes.add(route);
    }

    /**
     * Метод, добавляющий элемент {@link Route} в коллекцию Stack.
     * @param route - объект Route
     */
    public void addRoute(Route route) {
        routes.add(route);
    }

    /**
     * Метод, добавляющий объекты Route из ArrayList в коллекцию Stack.
     * @param routes - объект ArrayList<Route>
     */
    public void addRoutes(ArrayList<Route> routes) {
        Log.logger.log(Level.INFO,"Adding routes into stack");
        for (Route route : routes) {
            this.routes.push(route);
        }
    }

    /**
     * Метод, возвращающий индекс элемента в коллекции Stack по его ID.
     * @param id - поле id в Route
     * @return - индекс элемента в коллекции.
     */
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

    /**
     * Метод, возвращающий коллекцию Stack<Route>
     * @return - коллекция объектов Route типа Stack
     */
    public Stack<Route> getRoutes() {
        return routes;
    }

    /**
     * Метод, выводящий информацию о коллекции Stack
     */
    public void info() {
        System.out.printf("%s, Date of init:%s, Number of objects: %d%n", routes.getClass().getName(),
                dateOfInit.toString(), routes.size());
    }

    /**
     * Метод, очищающий коллекцию Stack<Route>
     */
    public void clear() {
        routes.removeAllElements();
    }

    /**
     * Метод, удаляющий элемент Route из коллекции Stack, если заданная дистанция эквивалента полю distance в Route
     * @param distance - дистанция
     */
    public void removeAllByDistance(double distance) {
        routes.removeIf(route -> distance == route.getDistance());
    }

    /**
     * Метод, возвращающий сумму полей distance объектов Route коллекции Stack
     */
    public void sumOfDistance() {
        double sum = 0;
        for (Route route : routes) {
            sum += route.getDistance();
        }
        System.out.printf("Sum of distance: %.2f%n", sum);
    }

    /**
     * Метод, выводящий все элементы типа Route коллекции Stack в стандартный поток вывода.
     */
    public void show() {
        for (Route route: routes) {
            showElement(route);
        }
    }

    /**
     * Метод, выводящий элемент типа Route в стандартный поток вывода.
     * @param route - элемент Route
     */
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

    /**
     * Метод, удаляющий первый элемент из коллекции Stack
     */
    public void removeFirst() {
        try {
            routes.pop();
        } catch (EmptyStackException ese) {
            System.err.println("The stack is empty");
        }

    }

    /**
     * Метод, удаляющий элемент коллекции по его полю ID.
     * @param id - поле id элемента Route
     */
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

    /**
     * Метод, удаляющий элемент коллекции Stack, стоящей на определенной позиции
     * @param index - позиция элемента в коллекции Stack
     */
    public void removeAt(int index) {
        try {
            routes.remove(index);
            System.out.println("removed 1 object");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("tried to remove non-existent element");
        }

    }

    /**
     * Метод, выводящий элементы Route коллекции Stack, который содержит в поле name заданную подстроку.
     * @param name - подстрока, по которой будет вестись поиск
     */
    public void filterContainsName(String name) {
        for (Route route : routes) {
            if (route.getName().contains(name)) {
                showElement(route);
            }
        }
    }

    /**
     * Метод, считывающий поля и создающий Route
     * @param nameArg - поле, полученное из аргумента команды
     * @param distanceArg - поле, полученное из аргумента команды
     * @param id - если id == -1, тогда создаем объект без id, иначе с id
     * @return - объект типа Route
     * @throws InvalidArgumentException - выбрасывается, если какой-то из аргументов не прошел валидацию
     */
    public Route readRoute(String nameArg, String distanceArg, int id) throws InvalidArgumentException{
        DataReader dataReader = new DataReader();
        Double.parseDouble(distanceArg);
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

    /**
     * Метод, обновляющий элемент с определенным значением поля ID объекта Route
     * @param id - id элемента
     * @param name - поле name объекта Route
     * @param coordinates - поле coordinates объекта Route
     * @param from - поле from объекта Route
     * @param to - поле to объекта Route
     * @param distance - поле distance объекта Route
     */
    public void updateId(int id, String name, Coordinates coordinates, FirstLocation from, SecondLocation to, double distance) {
        try {
            int index = findRouteById(id);
            Route route = new Route(name, coordinates, from, to, distance);
            routes.set(index, route);
        } catch (InvalidArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Метод, обновляющий элемент с определенным значением поля ID объекта Route
     * @param id - поле id элемента Route
     * @param route - объект Route
     */
    public void updateId(int id, Route route) {
        try {
            int index = findRouteById(id);
            routes.set(index, route);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("unknown element");
        }
    }

    /**
     * Класс, обеспечивающий считывание полей объектов Route из глобального потока ввода
     * @author spynad
     * @version govno
     */
    public static class DataReader {
        BufferedReader input = ClientManager.getInput();

        /**
         * Метод, который считывает поля объекта Coordinates и возвращает его
         * @return - объект Coordinates
         */
        public Coordinates readCoordinates() {
            while(true) {
                try {
                    System.out.println("enter coordinates fields(x, y): ");
                    String readStr = input.readLine();
                    String[] splitStr = readStr.split("\\s+");
                    if (splitStr.length != 2)
                        throw new InvalidArgumentException("expected 2 arguments; got " + splitStr.length);
                    return new Coordinates(Long.parseLong(splitStr[0]), Double.parseDouble(splitStr[1]));
                } catch (InvalidArgumentException e) {
                    System.err.println(e.getMessage());
                } catch (IOException ioe) {
                    System.err.println("read error");
                }
            }
        }

        /**
         * Метод, который считывает поля объекта FirstLocation и возвращает его
         * @return - объект FirstLocation
         */
        public FirstLocation readFirstLocation() {
            while(true) {
                try {
                    System.out.println("enter first location(x, y, name): ");
                    String readStr = input.readLine();
                    if (readStr.equals("")) {
                        return null;
                    }
                    String[] splitStr = readStr.split("\\s+");
                    if (splitStr.length != 3)
                        throw new InvalidArgumentException("expected 3 arguments; got " + splitStr.length);
                    return new FirstLocation(Integer.parseInt(splitStr[0]),
                            Long.parseLong(splitStr[1]),
                            splitStr[2]);
                } catch (InvalidArgumentException e) {
                    System.err.println(e.getMessage());
                } catch (IOException ioe) {
                    System.err.println("read error");
                }
            }
        }

        /**
         * Метод, который считывает поля объекта SecondLocation и возвращает его
         * @return - объект SecondLocation
         */
        public SecondLocation readSecondLocation() {
            while(true) {
                try {
                    System.out.println("enter second location(x, y, z): ");
                    String readStr = input.readLine();
                    String[] splitStr = readStr.split("\\s+");
                    if (splitStr.length != 3)
                        throw new InvalidArgumentException("expected 3 arguments; got " + splitStr.length);
                    return new SecondLocation(Integer.parseInt(splitStr[0]),
                            Long.parseLong(splitStr[1]),
                            Double.parseDouble(splitStr[2]));
                } catch (InvalidArgumentException e) {
                    System.err.println(e.getMessage());
                } catch (IOException ioe) {
                    System.err.println("read error");
                }
            }
        }
    }
}
