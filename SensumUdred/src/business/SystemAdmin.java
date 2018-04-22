package business;

import acq.IUser;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Søren Bendtsen
 */
public class SystemAdmin extends User {

    public SystemAdmin(String name, String id, String username, String password, String email) {
        super(0, name, id, username, password, email);
    }

    public IUser createUser(String name, String id, String username, String password, String email, int type) {
        User user = null;
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

}
