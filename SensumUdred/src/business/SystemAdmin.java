package business;

import acq.IUser;
import java.util.ArrayList;

/**
 *
 * @author Søren Bendtsen
 */
public class SystemAdmin extends User {

    public SystemAdmin(String name, String id, String userName, String password, String email) {
        super(0, name, id, userName, password, email);
    }

    public IUser createUser(String name, String id, String userName, String password, String email, int type) {
       User user;
        if (type == 1) {
            user = new SystemAdmin(name, id, userName, password, email);
        } else {
            user = new SocialWorker(name, id, userName, password, email);
        }
        IUser use = ((IUser) user);
        return use;
    }

    public boolean deleteUser(IUser user, ArrayList<IUser> users) {
        boolean userIsRemoved = false;
        if (users.contains(user)) {
            userIsRemoved = users.remove(user);
        }

        return userIsRemoved;
    }
    
    
    
    
//    public User createUser(String name, String id, String userName, String password, String email) {
//        //User user = new User(name, id, userName, password, email);
//        return user;
//    }

//    public boolean deleteUser(String username, ArrayList<User> users) {
//        boolean userIsRemoved = false;
////        for(User user : users) {
////            if(user.getUsername() == username) {
////              userIsRemoved = users.remove(user);
////            }
////        }
//        return userIsRemoved;
//    }

}
