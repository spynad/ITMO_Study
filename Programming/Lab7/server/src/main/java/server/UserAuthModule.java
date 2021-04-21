package server;

import data.UserDAO;
import exception.PersistentException;
import user.User;

public class UserAuthModule {
    private final UserDAO userDAO;

    public UserAuthModule(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean authUser(User user) {
        User authUser = userDAO.getUserWhere(user.getUsername());
        return authUser.getPassword().equals(user.getPassword());
    }

    public boolean registerUser(User user) {
        try {
            userDAO.insertUser(user);
            return true;
        } catch (PersistentException e) {
            return false;
        }
    }
}
