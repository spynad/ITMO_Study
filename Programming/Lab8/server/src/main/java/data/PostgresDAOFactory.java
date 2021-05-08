package data;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.sql.Connection;
import java.sql.SQLException;

public class PostgresDAOFactory extends DAOFactory {
    private static final String DATABASE_URL = "jdbc:postgresql://10.147.17.114/postgres";

    private static final ComboPooledDataSource cpds = new ComboPooledDataSource();

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    PostgresDAOFactory() {
        cpds.setJdbcUrl(DATABASE_URL);
        cpds.setUser("postgres");
        cpds.setPassword("pashaloh");

        cpds.setInitialPoolSize(5);
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(10);
    }

    public static Connection createConnection() throws SQLException {
        return cpds.getConnection();
    }

    @Override
    public PostgresRouteDAO getRouteDAO() {
        return new PostgresRouteDAO(validatorFactory);
    }

    @Override
    public PostgresUserDAO getUserDAO() {
        return new PostgresUserDAO();
    }
}
