/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acq;

import java.util.ArrayList;

/**
 *
 * @author Bruger
 */
public interface IData {

    public void saveUsers(ArrayList<IUser> data);

    public ArrayList<IUser> readUsers();

    public void logData(String logData);
    ArrayList<ICitizen> getCitizens();
    public void saveCitizens(ArrayList<ICitizen> list);

}
