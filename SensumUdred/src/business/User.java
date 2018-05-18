package business;

import acq.IUser;
import java.io.Serializable;

/**
 *
 * @author Nikolaj Filipsen
 */
public abstract class User implements IUser, Serializable {

    /**
     * The user's ID
     */
    private final String ID;

    /**
     * The user's information
     */
    private String name, username, password, email;

    /**
     *
     * Constructor used for creating a User.
     *
     * @param name the name of the user.
     * @param id the user's ID.
     * @param username the user's username, used when logging in.
     * @param password the user's password, used when logging in.
     * @param email the user's email.
     */
    public User(String name, String id, String username, String password, String email) {
        this.name = name;
        this.ID = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public abstract int getRole();

    /**
     * Returns a User as a nice looking String.
     *
     * @return a String.
     */
    @Override
    public String toString() {
        return String.format("Name: %-20s ID: %-12s Username: %-18s Mail: %-20s", name, ID, username, email);
    }

}
