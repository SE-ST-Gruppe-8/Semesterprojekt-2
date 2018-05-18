package business;

import acq.IUser;
import java.io.Serializable;

/**
 *
 * @author Søren Bendtsen
 */
public abstract class User implements IUser, Serializable {

    private final String ID;
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
    public void setName(String name) {
        this.name = name;
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
    public void setUsername(String userName) {
        this.username = userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public abstract int getRole();

    /**
     * Returns a User as a nice looking.
     *
     * @return a String.
     */
    @Override
    public String toString() {
        return String.format("Name: %-20s ID: %-12s Username: %-18s Mail: %-20s", name, ID, username, email);
    }

}
