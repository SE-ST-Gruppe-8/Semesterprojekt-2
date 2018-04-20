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

    private IData data;
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
        if (security.getActiveUser() instanceof SystemAdmin) {
            user = ((SystemAdmin) security.getActiveUser()).createUser(name, id, userName, password, email, type);
            if (user != null) {
                ArrayList<IUser> users = data.readData();
                users.add(user);
                data.writeData(users);
            }
        } else {
            System.out.println("Pwoblem OwO");
        }
    }

    @Override
    public void deleteUser(IUser user, ArrayList<IUser> users) {

        if (security.getActiveUser() instanceof SystemAdmin) {
            if (((SystemAdmin) security.getActiveUser()).deleteUser(user, users)) {
                security.logData("Deleted user " + user.toString());
            } else {
                System.out.println("User did not exist");
            }
        }

    }
}
