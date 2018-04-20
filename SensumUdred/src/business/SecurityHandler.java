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
        switch (user.getRole()) {
            case 1:
                this.activeUser = (SocialWorker)user;
                break;
            case 0:
                this.activeUser = (SystemAdmin)user;
                break;
            default:
                System.out.println("error in user role");
                break;
        }
        
    }
    
    public void logOutActiveUser(){
        this.activeUser = null;
    }

    public boolean validateUserLogin(String username, String password) {
        //if the login is true - find the user that matches the login info and set that user to activeUser
        return true;
    }

    public User getUserData(String username, String password) {
        return new SocialWorker("1","2","3","4","5");
    }
}
