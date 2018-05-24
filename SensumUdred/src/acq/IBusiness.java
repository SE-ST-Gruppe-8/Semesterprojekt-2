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

    /**
     * A method to create a case and save it in the system.
     *
     * @param id The ID of the user.
     * @param des The description of the user.
     * @param process The process of the user.
     * @param sw the socialworker that is selected to be the creater of the
     * case.
     * @param c the citizen that is selected to create the case for.
     */
    public void createCase(String id, String des, String process, ISocialWorker sw, ICitizen c);

    /**
     * A method to delete a user from the system.
     *
     * @param user the user who is to be deleted.
     */
    public void deleteUser(IUser user);

    /**
     * A method to check when a user is created, whether or not his ID is
     * unique.
     *
     * @param id the ID of a user.
     * @return Returns true if the ID is unique.
     */
    public boolean hasUniqueUserID(String id);

    /**
     * A method to check when a citizen is created, whether or not his ID is
     * unique.
     *
     * @param id the ID of a citizen.
     * @return Returns true if the ID is unique.
     */
    public boolean hasUnqiueCitizenID(String id);

    /**
     * A method to check when a user is created, whether or not his username is
     * unique.
     *
     * @param username The username of a citizen.
     * @return Returns true if the username is unique.
     */
    public boolean hasUniqueUsername(String username);

    /**
     * A method to check whether the id has a set amount of chars
     *
     * @param id The ID of a citizen or user.
     * @return Returns true if the ID follows the rules.
     */
    public boolean hasAcceptableID(String id);

    /**
     * A method to check whether the password is repeated correctly when
     * creating a user.
     *
     * @param password The password of a user.
     * @param repeatedPassword The repeatedPassword of a user.
     * @return Returns true if the passwords are identical.
     */
    public boolean hasAcceptablePassword(String password, String repeatedPassword);

    /**
     * A method to check whether the username has a certain length when creating
     * a user.
     *
     * @param username The username of a user.
     * @return Returns true if the length is acceptable.
     */
    public boolean hasAcceptableUsername(String username);

    /**
     * A method to check whether the mail has a certain length and contains the
     * "@" sign.
     *
     * @param mail The mail of a user.
     * @return Returns true if the length is acceptable, and the string contains
     * a "@".
     */
    public boolean hasAcceptableMail(String mail);

    /**
     * A method to check whether the name has a certain length.
     *
     * @param name The name of a user.
     * @return Returns true if the length is acceptable.
     */
    public boolean hasAcceptableName(String name);

    /**
     * A method to validate the username and password of a user.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return Returns true if the username and password matches the information
     * of an existing user.
     */
    public boolean validateUser(String username, String password);

    /**
     * A method to return a list of all the users.
     *
     * @return Returns the list of users.
     */
    public ObservableList<IUser> getUsers();

    /**
     * A method to return a list of all the inquiries.
     *
     * @return Returns the list of inquiries.
     */
    public ObservableList<IInquiry> getInquiries();

    /**
     * An old method to save the data of an edited inquiry.
     *
     * @param inquiry The inquiry to be saved.
     */
//    public void saveInquiry(IInquiry inquiry);

    /**
     * A method to log out from the system
     */
    public void logOutActiveUser();

    /**
     * A method to get the role of a specific user.
     *
     * @return Returns an integer.
     */
    public int getRole();

    /**
     * A method to return the active user of the system.
     *
     * @return Returns the active user.
     */
    public User getActiveUser();

    /**
     * A method to return the list of all the cases
     *
     * @return Returns the list of all cases.
     */
    public ObservableList<ICase> getCases();

    /**
     * A method to delete a case.
     *
     * @param newCase the case that is to be deleted.
     */
    public void deleteCase(ICase newCase);

    /**
     * A method to create a citizen.
     *
     * @param name The name of the citizen.
     * @param id The ID of the citizen.
     * @param needs The needs of the citizen.
     */
    public void createCitizen(String name, String id, String needs);

    /**
     * A method to return a list of all the citizens.
     *
     * @return Returns a list of all citizens.
     */
    public ObservableList<ICitizen> getCitizen();

    /**
     * A method to delete a citizen.
     *
     * @param citizen The citizen that is to be deleted.
     */
    public void deleteCitizen(ICitizen citizen);

    /**
     * A method to create an inquiry for a citizen.
     *
     * @param id The ID for the inquiry. (this parameter is redundant, because
     * the inquiry ID is determined by a citizens ID)
     * @param origin The origin of the inquiry.
     * @param informed The boolean to determine whether a citizen is informed of
     * the inquiry.
     * @param citizen The citizen for the inquiry.
     * @param description The description for the inquiry.
     */
    public void createInquiry(String id, String origin, boolean informed, ICitizen citizen, String description);

    /**
     * A method to delete a selected inquiry.
     *
     * @param i the inquiry to be deleted.
     */
    public void deleteInquiry(IInquiry i);

    /**
     * A method to edit description and process in a case.
     *
     * @param description The description of the case.
     * @param process The process of the case.
     * @param c The case to be edited.
     */
    public void editCase(String description, String process, ICase c);

    /**
     * A method to edit needs for a citizen.
     *
     * @param needs The needs of the citizen.
     * @param c The citizen to be edited.
     */
    public void editCitizen(String needs, ICitizen c);

    /**
     * A method to edit the description and isInformed for an inquiry.
     *
     * @param description The description.
     * @param i The inquiry to be edited.
     * @param isInformed The boolean for wheter the citizen has been informed.
     */
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

    /**
     * Gets a list of String arrays from the data facade. Each string array contains
     * information about citizens, the inquiries & cases connected to the citizens,
     * along with users assigned to the cases. The data is used for instantiating
     * classes and adding them to lists.
     */
    public void processStuff();

    /**
     * A method to clear user, case and inquiry lists.
     */
    public void clearLists();

    /**
     * A method to return the log in a list of strings.
     *
     * @return Returns the log in a list of strings.
     */
    public List<String> getLog();

}
