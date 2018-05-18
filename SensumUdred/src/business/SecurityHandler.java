package business;

import acq.IBusiness;
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
    private BusinessFacade business;

    public SecurityHandler(IData data, BusinessFacade businessFacade) {
        this.data = data;
        this.business = businessFacade;
//        activeUser = new SystemAdmin("brrt","brrt","brrt","brrt","brrt");
    }

    public void logDataToFile(String dataToBeLogged) {
        String log = new Date().toString() + "\t" + activeUser.toString() + "\t" + dataToBeLogged;
        System.out.println("Logging: " + log);
        data.logData(log);
    }

    public void logData(String dataToBeLogged) {
        System.out.println("Logging: " + new Date().toString() + "\t" + activeUser.toString() + "\t" + dataToBeLogged);
        data.logData(new Date().toString(), activeUser.getUsername(), dataToBeLogged);
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

    public boolean validateUserlogin(String[] userData, String password) {
        if (userData[3].equals(password)) {
            return createActiveUser(userData[0], userData[1], userData[2], userData[3], userData[4], Integer.parseInt(userData[5]));
        } else {
            return false;
        }
    }

    private boolean createActiveUser(String name, String id, String username, String password, String email, int type) {
        switch (type) {
            case 0:
                this.activeUser = new SystemAdmin(name, id, username, password, email);
                return true;
            case 1:
                this.activeUser = new SocialWorker(name, id, username, password, email);
                return true;
            default:
                this.activeUser = null;
                return false;
        }
    }

}
