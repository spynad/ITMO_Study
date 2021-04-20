package data;

import exception.PersistentException;
import exception.RouteBuildException;
import route.*;
import utils.DateLocalDateConverter;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class PostgresRouteDAO implements RouteDAO{

    @Override
    public Collection<Route> selectRoutesToCollection()  {
        try (Connection connection = PostgresDAOFactory.createConnection()) {
            Collection<Route> routes = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet r = statement.executeQuery("SELECT * FROM routes");
            while (r.next()) {
                RouteBuilder routeBuilder = new RouteBuilder();
                try {
                    buildRoite(r, routeBuilder);
                    routes.add(routeBuilder.buildWithId());
                } catch (RouteBuildException e) {
                    throw new PersistentException("UNKNOWN", e.getMessage());
                }
            }
            return routes;
        } catch (SQLException e) {
            throw new PersistentException(e.getErrorCode(), e.getMessage());
        }
    }

    @Override
    public Route getRoute(int id)  {
        try (Connection connection = PostgresDAOFactory.createConnection()) {
            Route route = null;
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM routes WHERE id=?");
            statement.setInt(1, id);
            ResultSet r = statement.executeQuery();
            while (r.next()) {
                RouteBuilder routeBuilder = new RouteBuilder();
                try {
                    buildRoite(r, routeBuilder);
                    route = routeBuilder
                            .buildWithId();
                } catch (RouteBuildException e) {
                    throw new PersistentException("UNKNOWN", e.getMessage());
                }
            }
            return route;
        } catch (SQLException e) {
            throw new PersistentException(e.getErrorCode(), e.getMessage());
        }
    }

    private void buildRoite(ResultSet r, RouteBuilder routeBuilder) throws RouteBuildException, SQLException {
        routeBuilder.setId(r.getInt(1));
        routeBuilder.setName(r.getString(2));
        routeBuilder.setCoordinates(new Coordinates(r.getLong(3), r.getDouble(4)));
        routeBuilder.setDate(DateLocalDateConverter.convertDateToLocalDate(r.getDate(5)));
        if (r.getInt(6) == 0 && r.getLong(7) == 0 && r.getString(8) == null) {
            routeBuilder.setFirstLocation(null);
        } else {
            routeBuilder.setFirstLocation(new FirstLocation(r.getInt(6), r.getLong(7), r.getString(8)));
        }
        routeBuilder.setSecondLocation(new SecondLocation(r.getInt(9), r.getLong(10), r.getDouble(11)));
        routeBuilder.setDistance(r.getDouble(13));
    }

    @Override
    public void insertRoute(Route route)  {
        try (Connection connection = PostgresDAOFactory.createConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO routes VALUES (nextval('id'), " +
                    "?,?,?,?,?,?,?,?,?,?,?,?)");
            statement.setString(1, route.getName());
            statement.setLong(2, route.getCoordinates().getX());
            statement.setDouble(3, route.getCoordinates().getY());
            statement.setDate(4, DateLocalDateConverter.convertLocalDateToDate(route.getCreationDate()));
            if (route.getFrom() != null) {
                statement.setInt(5, route.getFrom().getX());
                statement.setLong(6, route.getFrom().getY());
                statement.setString(7, route.getFrom().getName());
            } else {
                statement.setNull(5, Types.INTEGER);
                statement.setNull(6, Types.BIGINT);
                statement.setNull(7, Types.VARCHAR);
            }
            statement.setInt(8, route.getTo().getX());
            statement.setLong(9, route.getTo().getY());
            statement.setDouble(10, route.getTo().getZ());
            statement.setString(11, "spynad");
            statement.setDouble(12, route.getDistance());
            statement.execute();
        } catch (SQLException e) {
            throw new PersistentException(e.getErrorCode(), e.getMessage());
        }
    }

    @Override
    public boolean updateRoute(Route route) {
        throw new UnsupportedOperationException("update is not supported");
    }

    @Override
    public boolean deleteRoute(Route route)  {
        try (Connection connection = PostgresDAOFactory.createConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM routes WHERE id=?");
            statement.setInt(1, route.getId());
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new PersistentException(e.getErrorCode(), e.getMessage());
        }
    }
}
