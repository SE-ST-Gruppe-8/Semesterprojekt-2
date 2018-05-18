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
    /**
     * The active user in the system
     */
    private User activeUser;
    /**
     * a reference to the dataFacade
     */
    private IData data;
    
    /**
     * A constructor to create the securityHandler
     * @param data the interface for the dataFacade
     */
    public SecurityHandler(IData data) {
        this.data = data;
    }
    
    /**
     * @deprecated 
     * This method logs the data and adds a date and the activeUser. This will be saved to a file
     * @param dataToBeLogged The string you want to log
     */
    public void logDataToFile(String dataToBeLogged) {
        String log = new Date().toString() + "\t" + activeUser.toString() + "\t" + dataToBeLogged;
        System.out.println("Logging: " + log);
        data.logData(log);
    }
    
    /**
     * this method logs data that will be sent to the database
     * @param dataToBeLogged The string you want to log
     */
    public void logData(String dataToBeLogged) {
        System.out.println("Logging: " + new Date().toString() + "\t" + activeUser.toString() + "\t" + dataToBeLogged);
        data.logData(new Date().toString(), activeUser.getUsername(), dataToBeLogged);
    }
    
    /**
     * gets the current activeUser
     * @return the activeUser
     */
    public User getActiveUser() {
        return activeUser;
    }
    
    /**
     * sets the active user. 
     * If the user's role is 0 it will be an admin
     * If it's 1 it will be a socialWorker
     * @param user 
     */
    public void setActiveUser(IUser user) {
        switch (user.getRole()) {
            case 1:
                this.activeUser = (SocialWorker) user;
                break;
            case 0:
                this.activeUser = (SystemAdmin) user;
                break;
            default:
                System.out.println("Error in user role");
                break;
        }
    }
    
    /**
     * sets the activeUser to null so it will be logged out
     */
    public void logOutActiveUser() {
        this.activeUser = null;
    }
    
    /**
     * @deprecated 
     * this checks if the user login is valid.
     * If the user is valid, it will be logged in.
     * @param users a list of all users
     * @param username the username used for login
     * @param password the password used for login
     * @return true if the username and password matches a user
     */
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

    /**
     * this will validate the login from the database
     * @param userData the data from a user
     * @param password the password for login
     * @return true if the login is valid
     */
    public boolean validateUserlogin(String[] userData, String password) {
        if (userData[3].equals(password)) {
            return createActiveUser(userData[0], userData[1], userData[2], userData[3], userData[4], Integer.parseInt(userData[5]));
        } else {
            return false;
        }
    }

    /**
     * this will create an activeUser from the data in the constructor
     * @param name the name of the user
     * @param id the id of the user
     * @param username the username of the user
     * @param password the password for the user
     * @param email the user's email-address
     * @param type the type of user. 0 for admin and 1 for socialWorker
     * @return true if a user is created and set as activeUser. false if the activeUser is still null
     */
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
