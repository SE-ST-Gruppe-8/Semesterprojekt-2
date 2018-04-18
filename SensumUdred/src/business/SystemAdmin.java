package business;

/**
 *
 * @author Søren Bendtsen
 */
public class SystemAdmin extends User {

    public SystemAdmin(String name, String id, String userName, String password, String email) {
        super(name, id, userName, password, email);
    }

    public User createUser(String name, String id, String userName, String password, String email) {
        User user = new User(name, id, userName, password, email) {
        };
        return user;
    }

    public void deleteUser() {
        //Do stuff
    }

}
