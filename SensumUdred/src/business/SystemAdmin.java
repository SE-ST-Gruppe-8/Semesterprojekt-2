package business;

import acq.IUser;
import javafx.collections.ObservableList;

/**
 *
 * @author Søren Bendtsen
 */
public class SystemAdmin extends User {

    /**
     * A static final integer for SystemAdmin. The integer is unique from other
     * subclasses of User.
     */
    private static final int ROLE = 0;

    /**
     * An ID which makes sure that problems won't occure with binary files when
     * using Serilization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * A Constructor used for creating a SystemAdmin.
     *
     * @param name the admin's name.
     * @param id the admin's ID.
     * @param username the admin's username.
     * @param password the admin's password.
     * @param email the admin's email.
     */
    public SystemAdmin(String name, String id, String username, String password, String email) {
        super(name, id, username, password, email);
    }

    /**
     * Creates a new IUser and returns it. The specific type of IUser returned
     * depends on the role.
     *
     * @param name the user's name.
     * @param id the user's ID.
     * @param username the user's username.
     * @param password the user's password.
     * @param email the user's email.
     * @param role the user's role.
     * @return A newly created IUser.
     */
    public IUser createUser(String name, String id, String username, String password, String email, int role) {
        IUser user = null;
        if (role == ROLE) {
            user = new SystemAdmin(name, id, username, password, email);
        } else if (role == SocialWorker.getSWRole()) {
            user = new SocialWorker(name, id, username, password, email);
        }
        return user;
    }

    /**
     * If the specified IUser exists in the specified list it will be removed.
     *
     * @param user an IUser.
     * @param users an OservableList of IUser objects.
     * @return true if the list contained the specified IUser.
     */
    public boolean deleteUser(IUser user, ObservableList<IUser> users) {
        boolean userIsRemoved = false;
        if (users.contains(user)) {
            userIsRemoved = users.remove(user);
        }
        return userIsRemoved;
    }

    @Override
    public int getRole() {
        return 0;
    }

    /**
     * A static method that returns the integer representing the role of
     * SystemAdmin.
     *
     * @return the role of a SystemAdmin.
     */
    public static int getAdminRole() {
        return ROLE;
    }
}
