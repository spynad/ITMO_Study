package data;

import exception.PersistentException;
import user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PostgresUserDAO implements UserDAO{

    @Override
    public User getUserWhere(String username) {
        try (Connection connection = PostgresDAOFactory.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            return new User(resultSet.getString(1), resultSet.getString(2));
        } catch (SQLException e) {
            throw new PersistentException(e.getErrorCode(), e.getMessage());
        }
    }

    @Override
    public void insertUser(User user) {
        try (Connection connection = PostgresDAOFactory.createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users VALUES (?,?)");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new PersistentException(e.getErrorCode(), e.getMessage());
        }
    }

    @Override
    public boolean deleteUser(User user) {
        try (Connection connection = PostgresDAOFactory.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE username = ?");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new PersistentException(e.getErrorCode(), e.getMessage());
        }
    }
}
