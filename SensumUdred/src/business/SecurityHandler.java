package business;

import java.util.Date;

/**
 *
 * @author J
 * @author Robin
 */
public class SecurityHandler {

    private User activeUser;

    public SecurityHandler() {
        activeUser = null;
    }

    public String logData(String data) {
        return activeUser.toString() + new Date().toString() + data;
    }
    
    public User getActiveUser(){
        return activeUser;
    }
    
    public void setActiveUser(User user){
        this.activeUser = user;
    }

    public boolean validateUserLogin(String username, String password) {
        //if the login is true - find the user that matches the login info and set that user to activeUser
    }

    public User getUserData(String username, String password) {
        
    }
}
