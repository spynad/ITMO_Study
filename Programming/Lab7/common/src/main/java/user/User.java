package user;

import hash.SHA224Generator;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 8347617547303456361L;

    private final String username;
    private final String password;

    public User(String username, String password) {
        this.username = username;
        this.password = SHA224Generator.getHash(password);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
