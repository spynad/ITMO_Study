package data;

public abstract class DAOFactory {
    public abstract PostgresRouteDAO getRouteDAO();
    public abstract PostgresUserDAO getUserDAO();

    public static DAOFactory getDAOFactory() {
        return new PostgresDAOFactory();
    }
}
