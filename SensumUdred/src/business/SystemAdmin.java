package business;

import java.util.ArrayList;

/**
 *
 * @author Søren Bendtsen
 */
public class SystemAdmin extends User {

    public SystemAdmin(String name, String id, String userName, String password, String email) {
        super(0, name, id, userName, password, email);
    }

//    public User createUser(String name, String id, String userName, String password, String email) {
//        //User user = new User(name, id, userName, password, email);
//        return user;
//    }

    public boolean deleteUser(String username, ArrayList<User> users) {
        boolean userIsRemoved = false;
//        for(User user : users) {
//            if(user.getUsername() == username) {
//              userIsRemoved = users.remove(user);
//            }
//        }
        return userIsRemoved;
    }

}