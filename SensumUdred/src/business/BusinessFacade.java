/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import acq.*;

/**
 *
 * @author Bruger
 */
public class BusinessFacade implements IBusiness {

    private IData data;

    public BusinessFacade() {
    }

    @Override
    public void injectData(IData dataLayer) {
        data = dataLayer;
    }
    
    public void createUser(String name, String id, String userName, String password, String email){
//        User u = SecurityHandler.activeUser.createUser(name,id,userName,password,email);
//        DataFacade.save(u.getName()+u.getPassword(),"users");
//        SecurityHandler.logData("Created: "+u.getName());
    }
    
    public void deleteUser(String username, Arraylist<User> users) {
        
    }
}
