package business;

import acq.IData;
import acq.IUser;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author J
 * @author Robin
 */
public class SecurityHandler {

    private User activeUser;
    private IData data;

    public SecurityHandler(IData data) {
        this.data = data;
        activeUser = null;
    }

    public void logData(String dataToBeLogged) {
        String log = activeUser.toString()+ "\t" + new Date().toString()+ "\t" + dataToBeLogged;
        System.out.println("Logging: " + log);
        data.logData(log);
        }

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(IUser user) {
        switch (user.getRole()) {
            case 1:
                this.activeUser = (SocialWorker) user;
                break;
            case 0:
                this.activeUser = (SystemAdmin) user;
                break;
            default:
                System.out.println("error in user role");
                break;
        }

    }

    public void logOutActiveUser() {
        this.activeUser = null;
    }

    public boolean validateUserLogin(ArrayList<IUser> users, String username, String password) {
        //if the login is true - find the user that matches the login info and set that user to activeUser
        for (IUser u : users) {
            if (u.getUsername().equals(username)) {
                if (u.getPassword().equals(password)) {
                    setActiveUser(u);
                    return true;
                }
            }
        }
        return false;
    }
}
