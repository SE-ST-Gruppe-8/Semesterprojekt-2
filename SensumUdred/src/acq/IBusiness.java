/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acq;

import business.User;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Nikolaj Filipsen
 */
public interface IBusiness {

    /**
     * Injects the data layer into the business layer.
     *
     * @param dataLayer the data layer.
     */
    void injectData(IData dataLayer);

    /**
     * A method to create a user and save it in the system.
     *
     * @param name The name of the user.
     * @param id The ID of the user.
     * @param userName The username of the user.
     * @param password The password for the user.
     * @param email The email for the user.
     * @param role The role of user.
     */
    public void createUser(String name, String id, String userName, String password, String email, int role);

    public void createCase(String id, String des, String process, ISocialWorker sw, ICitizen c);

    /**
     * A method to delete a user from the system.
     *
     * @param user the user who is to be deleted.
     */
    public void deleteUser(IUser user);

    public boolean hasUniqueUserID(String id);

    public boolean hasUnqiueCitizenID(String id);

    public boolean hasUniqueUsername(String username);

    public boolean hasAcceptableID(String id);

    public boolean hasAcceptablePassword(String password, String repeatedPassword);

    public boolean hasAcceptableUsername(String username);

    public boolean hasAcceptableMail(String mail);

    public boolean hasAcceptableName(String name);

    /**
     * A method to validate the username and password of a user.
     *
     * @param username the username.
     * @param password the password.
     * @return true if the username and password matches the information of an
     * existing user.
     */
    public boolean validateUser(String username, String password);

    public ObservableList<IUser> getUsers();

    public ObservableList<IInquiry> getInquiries();

    public void saveInquiry(IInquiry inquiry);

    public void logOutActiveUser();

    public int getRole();

    public User getActiveUser();

    public ObservableList<ICase> getCases();

    public void deleteCase(ICase newCase);

    public void createCitizen(String name, String id, String needs);

    public ObservableList<ICitizen> getCitizen();

    public void deleteCitizen(ICitizen citizen);

    public void createInquiry(String id, String origin, boolean informed, ICitizen citizen, String description);

    public void deleteInquiry(IInquiry i);

    public void editCase(String description, String process, ICase c);

    public void editCitizen(String needs, ICitizen c);

    public void editInquiry(String description, IInquiry i, boolean isInformed);

    /**
     * Returns an array of static final integers.
     *
     * @return an array of static final integers.
     */
    public int[] getFinalInts();

    /**
     * Returns the integer representing the role of SocialWorker.
     *
     * @return the role of a SocialWorker.
     */
    public int getSocialWorkerRoleInt();

    /**
     * Returns the integer representing the role of SystemAdmin.
     *
     * @return the role of a SystemAdmin.
     */
    public int getAdminRoleInt();

    public void processStuff();

    public void clearLists();

    public List<String> getLog();

}
