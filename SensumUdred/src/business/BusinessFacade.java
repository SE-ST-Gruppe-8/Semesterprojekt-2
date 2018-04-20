package business;

import acq.*;
import java.util.ArrayList;

/**
 *
 * @author Bruger
 */
public class BusinessFacade implements IBusiness {

    private IData data;
    private SecurityHandler security;

    public BusinessFacade() {
        security = new SecurityHandler();
    }
    /**
     * A method to inject the data layer into the business layer
     * @param dataLayer 
     */
    @Override
    public void injectData(IData dataLayer) {
        data = dataLayer;
    }
    
    /**
     * This method is only a test method and should not be used in this state
     */
//    public void createCase(){
//        ArrayList<String> testArray = new ArrayList<>();
//        testArray.add(security.logData(((SocialWorker)security.getActiveUser()).createCase()));
//        data.writeData(testArray, "LogFile.txt");
//       
//    }
    /**
     * a method to create a user in the system
     * @param name name of the user
     * @param id id of the user
     * @param userName the username of the user
     * @param password the password for the user
     * @param email the email for the user
     * @param type the type of user: 0 for SystemAdmin, 1 for SocialWorker
     */
    @Override
    public void createUser(String name, String id, String userName, String password, String email, int type) {
        IUser user;
        if (security.getActiveUser() instanceof SystemAdmin) {
            user = ((SystemAdmin) security.getActiveUser()).createUser(name, id, userName, password, email, type);
            if (user != null) {
                ArrayList<IUser> users = data.readUsers();
                users.add(user);
                data.saveUsers(users);
            }
        } else {
            System.out.println("error, could not create user");
        }
    }
    /**
     * a method to delete a user from the system
     * @param username
     * @param users 
     */
    @Override
    public void deleteUser(IUser user, ArrayList<IUser> users) {

        if (security.getActiveUser() instanceof SystemAdmin) {
            if (((SystemAdmin) security.getActiveUser()).deleteUser(user, users)) {
                security.logData("Deleted user " + user.toString());
            } else {
                System.out.println("User did not exist");
            }
        }
    }

    /**
     * a method to validate the username and password of a user
     * @param username
     * @param password
     * @return 
     */
    @Override
    public boolean validateUser(String username, String password) {
        return security.validateUserLogin(data.readUsers(), username, password);
    }
}
