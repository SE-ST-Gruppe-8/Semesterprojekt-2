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
    private ObservableList<ICitizen> citizens;
    private ObservableList<ICase> cases;

    @Override
    public ObservableList<ICase> getCases() {
        ArrayList<ICase> cases = new ArrayList<>();
        data.loadData(cases, "cases");
        return this.cases = FXCollections.observableArrayList(cases);
    }

    @Override
    public ObservableList<IUser> getUsers() {
        ArrayList<IUser> users = new ArrayList<>();
        data.loadData(users, "users");
        return this.users = FXCollections.observableArrayList(users);
    }

    public BusinessFacade() {
    }

    @Override
    public void logOutActiveUser() {
        security.logOutActiveUser();
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
                data.saveData((ArrayList<IUser>) users.stream().collect(Collectors.toList()), "users");
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
                data.saveData((ArrayList<IUser>) users.stream().collect(Collectors.toList()), "users");
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
        ArrayList<IUser> users = new ArrayList<>();
        data.loadData(users, "users");
        if (security.validateUserLogin(users, username, password)) {
            data.logData(username + " logged in.");
            return true;
        } else {
            data.logData("Login attempt with username: " + username);
            return false;
        }
    }

    @Override
    public ObservableList<IInquiry> getInquiries() {
        ArrayList<ICitizen> citizens = new ArrayList<>();
        data.loadData(citizens, "citizens");
        for (ICitizen c : citizens) {
            inquiries.add(c.getInquiry());
        }
        return inquiries;
    }
    
    @Override
    public void saveInquiry(IInquiry inquiry) {
        ArrayList<ICitizen> citizens = new ArrayList<>();
        data.loadData(citizens, "citizens");
        Citizen c = inquiry.getCitizen();
        citizens.remove(c);
        c.setInquiry((Inquiry) inquiry);
        citizens.add(c);
        data.saveData(citizens, "citizens");
    }

//    public void tester() {
//        ArrayList<ICitizen> citizens = new ArrayList<>();
//        for (int i = 1; i <= 10; i++) {
//            citizens.add(new Citizen("Citizen" + (i), String.valueOf(i), "needs" + i));
//        }
//        for (ICitizen c : citizens) {
//            c.createInquiry(String.valueOf(c.getId()), "origin" + c.getId(), true, "description");
//        }
//        data.saveCitizens(citizens);
//    }
    public int getRole() {
        return security.getActiveUser().getRole();
    }

    @Override
    public void createCase(String id, String des, String process, ISocialWorker sw, ICitizen c) {
        String s = "error, could not create case";
        ICase newCase;
        if (security.getActiveUser() instanceof SocialWorker) {
            newCase = ((ISocialWorker) security.getActiveUser()).createCase(id, des, process, sw, c);
            if (newCase != null) {
                cases.add(newCase);
                data.saveData((ArrayList<ICase>) cases.stream().collect(Collectors.toList()), "cases");
                security.logData("Created case with id: " + id);
            } else {
                System.out.println(s);
            }

        }
        System.out.println("joe" + id);
    }

    @Override
    public User getActiveUser() {
        return security.getActiveUser();
    }

    @Override
    public void deleteCase(ICase newCase) {
        if (security.getActiveUser() instanceof SocialWorker) {
            if (((SocialWorker) security.getActiveUser()).deleteCase(newCase, cases)) {
                security.logData("Deleted case " + newCase.toString());
                data.saveData((ArrayList<ICase>) cases.stream().collect(Collectors.toList()), "cases");
            } else {
                System.out.println("Case did not exist");
            }
        }
    }

    @Override
    public ObservableList<ICitizen> getCitizens() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveCitizens() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void createCitizen(String name, String id, String needs) {
        ICitizen citizen;
        String s = "Error";
        if (security.getActiveUser() instanceof SocialWorker) {
            citizen = ((ISocialWorker) security.getActiveUser()).createCitizen(name, id, needs);
            if (citizen != null) {
                citizens.add(citizen);
                data.saveData((ArrayList<ICase>) cases.stream().collect(Collectors.toList()), "Citizens");
                security.logData("Created case with id: " + id);
            } else {
                System.out.println(s);
            }
            
        }

    }
    
}
