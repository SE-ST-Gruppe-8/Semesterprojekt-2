/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import acq.*;
import java.util.ArrayList;

/**
 *
 * @author Bruger
 */
public class BusinessFacade implements IBusiness {

    public IData data;
    private SecurityHandler security;

    public BusinessFacade() {
        security = new SecurityHandler();
    }

    @Override
    public void injectData(IData dataLayer) {
        data = dataLayer;
    }

    @Override
    public void createUser(String name, String id, String userName, String password, String email, int type) {
        IUser user;
        if (true) {
//            user = ((SystemAdmin) security.getActiveUser()).createUser(name, id, userName, password, email, type);
            SystemAdmin brrt = new SystemAdmin("somthing", "is", "written", "here", "password");
            IUser u = brrt.createUser(name, id, userName, password, email, type);
            ArrayList<IUser> users = data.readData();
            users.add(u);
            data.writeData(users);
        } else {
            System.out.println("Pwoblem OwO");
        }
        System.out.println(data.readData());
    }

    @Override
    public void deleteUser(String username, ArrayList<User> users) {
//        if(SecurityHandler.activeUser.deleteUser(username, users)) {
//            SecurityHandler.logData("Deleted user "+ username);
//        } else {
//            System.out.println("User did not exist");
//        }
    }

    public static void main(String[] args) {
        User user = new SystemAdmin("somthing", "is", "written", "here", "password");
        SecurityHandler s = new SecurityHandler();
        s.setActiveUser(user);
        BusinessFacade bf = new BusinessFacade();
        
        bf.createUser("somthing", "is", "written", "here", "password", 2);
        System.out.println(bf.data.readData());
    }
}
