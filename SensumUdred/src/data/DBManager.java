/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import acq.IUser;

/**
 *
 * @author Frederik
 */
public class DBManager {

    //Login to elephant-sql
    private String dburl = "postgres://flugkwex:361Fvzl2AmnhDLVqPtBhomyr0-ojDBBC@horton.elephantsql.com:5432/flugkwex";
    private String dbusername = "flugkwex";
    private String dbpassword = "361Fvzl2AmnhDLVqPtBhomyr0-ojDBBC";

    public void saveUser(IUser user) {
        //String id = user.getID()
    }

    public void writeToDB() {

    }

}
