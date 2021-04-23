package server;

import data.UserDAO;
import exception.PersistentException;
import user.User;

public class UserAuthModule {
    private final UserDAO userDAO;
    private User user;

    public UserAuthModule(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean authUser(User user) {
        User authUser = userDAO.getUserWhere(user.getUsername());
        if (authUser.getPassword().equals(user.getPassword())) {
            this.user = user;
            return true;
        } else {
            this.user = null;
            return false;
        }
    }

    public boolean registerUser(User user) {
        try {
            userDAO.insertUser(user);
            return true;
        } catch (PersistentException e) {
            return false;
        }
    }

    public User getUser() {
        System.out.println(user);
        return user;
    }
}
