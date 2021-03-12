package main;

import exception.RouteBuildException;
import io.UserIO;
import log.Log;
import route.*;

import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;

/**
 * Класс, которой реализует взаимодействие с коллекцией типа Stack
 * @author spynad
 * @version govno
 */
public class RouteStackManager implements RouteCollectionManager {

    /**
     * Собственно говоря, сама коллекция Stack объектов Route
     */
    private final Stack<Route> routes;

    private final UserIO userIO;

    /**
     * Множество уникальных значений ID Route
     */
    private final SortedSet<Integer> setId = new TreeSet<>();

    private final LocalDate dateOfInit = LocalDate.now();

    public RouteStackManager(UserIO userIO) {
        Log.logger.log(Level.INFO,"RouteManager init");
        routes = new Stack<>();
        this.userIO = userIO;
    }

    /**
     * Метод, создающий и добавляющий элемент {@link Route} в коллекцию Stack.
     * @param name - поле name элемента Route
     * @param coordinates - поле coordinates элемента Route
     * @param from - поле from элемента Route
     * @param to - поле to элемента Route
     * @param distance - поле distance элемента Route
     * @throws NumberFormatException - -//-
     */
    public void addRoute(String name, Coordinates coordinates, FirstLocation from, SecondLocation to, double distance)
            throws RouteBuildException, NumberFormatException {
        Route route = createRoute(name, coordinates, from, to, distance);
        routes.add(route);
    }

    public void addRoute(Route route) {
        routes.add(route);
    }

    public Route createRoute(String name, Coordinates coordinates, FirstLocation from, SecondLocation to, double distance)
            throws RouteBuildException, NumberFormatException {
        int id = 1;
        for (int idSearch : setId) {
            if (idSearch != id) {
                id = idSearch - 1;
                break;
            }
            id++;
        }
        RouteBuilder routeBuilder = new RouteBuilder();
        Route route = routeBuilder.setId(id)
                .setName(name)
                .setCoordinates(coordinates)
                .setFirstLocation(from)
                .setSecondLocation(to)
                .setDistance(distance)
                .buildWithId();
        addUniqueID(id);
        return route;
    }

    public boolean addUniqueID(int id) {

        return setId.add(id);
    }

    public void removeUniqueID(int id) {
        setId.remove(id);
    }

    /**
     * Метод, добавляющий объекты Route из ArrayList в коллекцию Stack.
     * @param routes - объект ArrayList<Route>
     */
    public void addRoutes(List<Route> routes) {
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
        userIO.printLine(String.format("%s, Date of init:%s, Number of objects: %d", routes.getClass().getName(),
                dateOfInit.toString(), routes.size()));
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
        userIO.printLine(String.format("Sum of distance: %.2f%n", sum));
    }

    /**
     * Метод, выводящий все элементы типа Route коллекции Stack в стандартный поток вывода.
     */
    public void show() {
        RouteFormatter formatter = new RouteFormatter();
        for(Route route: routes) {
            userIO.printLine(formatter.formatRoute(route));
        }
    }

    /**
     * Метод, удаляющий первый элемент из коллекции Stack
     */
    public void removeFirst() {
        try {
            removeUniqueID(routes.get(0).getId());
            routes.remove(0);
        } catch (EmptyStackException | ArrayIndexOutOfBoundsException ese) {
            System.err.println("The stack is empty");
            Log.logger.log(Level.WARNING, "EXCEPTION: ", ese);
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
                removeUniqueID(route.getId());
                iterator.remove();
                count++;
            }
        }
        userIO.printLine(String.format("removed %d object(-s)%n", count));
    }

    /**
     * Метод, удаляющий элемент коллекции Stack, стоящей на определенной позиции
     * @param index - позиция элемента в коллекции Stack
     */
    public void removeAt(int index) {
        try {
            removeUniqueID(routes.get(index).getId());
            routes.remove(index);
            userIO.printLine("removed 1 object");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("tried to remove non-existent element");
        }
    }

    /**
     * Метод, выводящий элементы Route коллекции Stack, который содержит в поле name заданную подстроку.
     * @param name - подстрока, по которой будет вестись поиск
     */
    public void filterContainsName(String name) {
        RouteFormatter routeFormatter = new RouteFormatter();
        for (Route route : routes) {
            if (route.getName().contains(name)) {
                userIO.printLine(routeFormatter.formatRoute(route));
            }
        }
    }


    public void updateId(int id, Route route)
            throws NumberFormatException{
        int index = findRouteById(id);
        route.setId(id);
        routes.set(index, route);
    }
}
