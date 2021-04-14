package data;

import java.sql.*;

public class DBConnectionManager {
    private Connection connection;

    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String DATABASE_URL = "jdbc:postgresql://192.168.1.5/spynad";

    public void establishConnection() throws ClassNotFoundException, SQLException {
        //TODO: temp
        Class.forName(JDBC_DRIVER);
        connection = DriverManager.getConnection(DATABASE_URL, "postgres", "");
        System.out.println("test");
    }

    public Connection getConnection() {
        return connection;
    }
}
