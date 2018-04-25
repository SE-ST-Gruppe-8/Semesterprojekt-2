package business;

import acq.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Bruger
 */
public class BusinessFacade implements IBusiness {

    private IData data;
    private SecurityHandler security;
    private ObservableList<IUser> users;
    private ObservableList<IInquiry> inquiries = FXCollections.observableArrayList();

    @Override
    public ObservableList<IUser> getUsers() {
        return users = FXCollections.observableArrayList(data.readUsers());
    }

    public BusinessFacade() {
    }

    /**
     * A method to inject the data layer into the business layer
     *
     * @param dataLayer
     */
    @Override
    public void injectData(IData dataLayer) {
        data = dataLayer;
        security = new SecurityHandler(data);
        //tester(); //creates citizens with inquires
    }

    /**
     * a method to create a user in the system
     *
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
//                ArrayList<IUser> users = data.readUsers();
//                users.add(user);
//                data.saveUsers(users);
                System.out.println("meow");
                users.add(user);
                data.saveUsers((ArrayList<IUser>) users.stream().collect(Collectors.toList()));
                security.logData("Created user: " + userName);
            }
        } else {
            System.out.println("error, could not create user");
        }
    }

    /**
     * a method to delete a user from the system
     *
     * @param user
     * @param users
     */
    @Override
    public void deleteUser(IUser user) {
        if (security.getActiveUser() instanceof SystemAdmin) {
            if (((SystemAdmin) security.getActiveUser()).deleteUser(user, users)) {
                security.logData("Deleted user " + user.toString());
                data.saveUsers((ArrayList<IUser>) users.stream().collect(Collectors.toList()));
            } else {
                System.out.println("User did not exist");
            }
        }
    }

    /**
     * a method to validate the username and password of a user
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean validateUser(String username, String password) {
        if (security.validateUserLogin(data.readUsers(), username, password)) {
            data.logData(username + " logged in.");
            return true;
        } else {
            data.logData("Login attempt with username: " + username);
            return false;
        }
    }

    @Override
    public ObservableList<IInquiry> getInquiries() {
        ArrayList<ICitizen> citizens = data.getCitizens();
        for(ICitizen c : citizens) {
            inquiries.add(c.getInquiry());
        }
        return inquiries;
    }
    
    public void tester() {
        ArrayList<ICitizen> citizens = new ArrayList<>();
        for(int i = 1; i <= 10; i++){
            citizens.add(new Citizen("Citizen"+(i), String.valueOf(i), "needs"+i));
        }
        for(ICitizen c : citizens){
            c.createInquiry(String.valueOf(c.getId()), "origin"+c.getId(), true);
        }
        data.saveCitizens(citizens);
    }
}
