package business;

import acq.IUser;
import javafx.collections.ObservableList;

/**
 *
 * @author Søren Bendtsen
 */
public class SystemAdmin extends User {

    private static final int ROLE = 0;
    private static final long serialVersionUID = 1L;

    public SystemAdmin(String name, String id, String username, String password, String email) {
        super(name, id, username, password, email);
    }

    public IUser createUser(String name, String id, String username, String password, String email, int type) {
        IUser user = null;
        switch (type) {
            case 0:
                user = new SystemAdmin(name, id, username, password, email);
                break;

            case 1:
                user = new SocialWorker(name, id, username, password, email);
                break;

            default:
                System.out.println("User type does not exist");
        }
        return user;
    }

    public boolean deleteUser(IUser user, ObservableList<IUser> users) {
        boolean userIsRemoved = false;
        if (users.contains(user)) {
            System.out.println("brrt");
            userIsRemoved = users.remove(user);
        }

        return userIsRemoved;
    }

    @Override
    public int getRole() {
        return 0;
    }

    public static int getAdminRole() {
        return ROLE;
    }
}
