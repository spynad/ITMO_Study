package collection;

import data.RouteDAO;
import locale.ServerBundle;
import response.Creator;
import route.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс, которой реализует взаимодействие с коллекцией типа Stack
 * @author spynad
 * @version govno
 */
public class RouteStackManager implements RouteCollectionManager {

    private Stack<Route> routes;

    private final Creator creator;

    private final SortedSet<Integer> setId = new TreeSet<>();

    private final LocalDate dateOfInit = LocalDate.now();

    private final RouteDAO routeDAO;

    public RouteStackManager(Creator creator, RouteDAO routeDAO) {
        routes = new Stack<>();
        this.creator = creator;
        this.routeDAO = routeDAO;
    }

    public void addRouteId(Route route) {
        int id = 1;
        for (int idSearch : setId) {
            if (idSearch != id) {
                id = idSearch - 1;
                break;
            }
            id++;
        }

        route.setId(id);
        routes.add(route);
        addUniqueID(id);
    }

    public void addRoute(Route route) {
        routes.add(route);
        addUniqueID(route.getId());
    }

    public void addRoutes(Collection<Route> routes) {
        this.routes.addAll(routes);
    }

    public boolean addUniqueID(int id) {
        return setId.add(id);
    }

    public void removeUniqueID(int id) {
        setId.remove(id);
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
        return index;
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
        creator.addToMsg(String.format(ServerBundle.getString("collection.info"), routes.getClass().getName(),
                dateOfInit.toString(), routes.size()));
    }

    /**
     * Метод, очищающий коллекцию Stack<Route>
     */
    public void clear() {
        routes.removeAllElements();
        setId.clear();
    }

    /**
     * Метод, удаляющий элемент Route из коллекции Stack, если заданная дистанция эквивалента полю distance в Route
     * @param distance - дистанция
     */
    public void removeAllByDistance(double distance) {
        routes.stream().filter(x -> x.getDistance() == distance).forEach(x -> removeUniqueID(x.getId()));
        //TODO: think of the better method of doing that
        ArrayList<Route> routesToDelete = routes.stream()
                .filter(x -> x.getDistance() == distance)
                .collect(Collectors.toCollection(ArrayList::new));
        routes = routes.stream()
                .filter(x -> x.getDistance() != distance)
                .collect(Collectors.toCollection(Stack::new));
        for (Route route : routesToDelete) {
            routeDAO.deleteRoute(route);
        }
    }

    /**
     * Метод, возвращающий сумму полей distance объектов Route коллекции Stack
     */
    public void sumOfDistance() {
        creator.addToMsg(String.format(ServerBundle.getString("collection.sum_of_distance"), routes.stream()
                .map(Route::getDistance)
                .reduce(Double::sum)
                .get()));
    }

    /**
     * Метод, выводящий все элементы типа Route коллекции Stack в стандартный поток вывода.
     */
    public void show() {
        RouteFormatter formatter = new RouteFormatter();
        routes.stream()
                .sorted()
                .forEach(x -> creator.addToMsg(formatter.formatRoute(x)));
    }

    /**
     * Метод, удаляющий первый элемент из коллекции Stack
     */
    public void removeFirst() {
        try {
            removeUniqueID(routes.get(0).getId());
            routes = routes.stream()
                    .filter(x -> !x.getId().equals(routes.get(0).getId()))
                    .collect(Collectors.toCollection(Stack::new));
        } catch (EmptyStackException | ArrayIndexOutOfBoundsException ese) {
            creator.addToMsg(ServerBundle.getString("collection.empty_stack"));
        }
    }

    /**
     * Метод, удаляющий элемент коллекции по его полю ID.
     * @param id - поле id элемента Route
     */
    public void removeById(int id) {
        routes.stream().filter(x -> id == x.getId()).forEach(x -> removeUniqueID(x.getId()));
        routes = routes.stream()
                .filter(x -> id != x.getId())
                .collect(Collectors.toCollection(Stack::new));
    }

    /**
     * Метод, удаляющий элемент коллекции Stack, стоящей на определенной позиции
     * @param index - позиция элемента в коллекции Stack
     */
    public void removeAt(int index) {
        try {
            removeUniqueID(routes.get(index).getId());
            routes.remove(index);
            creator.addToMsg(ServerBundle.getString("collection.removed_one"));
        } catch (ArrayIndexOutOfBoundsException e) {
            creator.addToMsg(ServerBundle.getString("collection.unexistent_element"));
        }
    }

    /**
     * Метод, выводящий элементы Route коллекции Stack, который содержит в поле name заданную подстроку.
     * @param name - подстрока, по которой будет вестись поиск
     */
    public void filterContainsName(String name) {
        RouteFormatter routeFormatter = new RouteFormatter();
        routes.stream()
                .filter(x -> x.getName().contains(name))
                .forEach(x -> creator.addToMsg(routeFormatter.formatRoute(x)));
    }


    public void updateId(int id, Route route)
            throws NumberFormatException{
        int index = findRouteById(id);
        route.setId(id);
        routes.set(index, route);
    }
}
