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
    
    @Override
    public String toString() {
        String s = String.format("Name: %-20s ID: %-12s Username: %-18s Mail: %-20s", name, ID, username, email);
        System.out.println(s);
        return s;
//        return "Name: " + name + "\tID: " + ID + "\tUsername: " + username + "\tMail: " + email;
    }
}
