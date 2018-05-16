package business;

import acq.IUser;
import java.io.Serializable;

/**
 *
 * @author Søren Bendtsen
 */
public abstract class User implements IUser, Serializable {
    
    private String name, ID, username, password, email;

    public User(String name, String id, String username, String password, String email) {
        this.name = name;
        this.ID = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public abstract int getRole();
    
    public String toString(){
        return name + "\t" + ID + "\t" + username + "\t" + email;
    }
}
