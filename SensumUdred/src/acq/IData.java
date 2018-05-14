/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acq;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruger
 */
public interface IData {

    public boolean hasUniqueUserUD(String id);

    public boolean hasUniqueCitizenID(String id);

//    public void saveUsers(ArrayList<IUser> data);
//    
//    public void saveCases(ArrayList<ICase> data);
//
    public List<String[]> readUsers();

    public void saveUsers(IUser user);
    
    public void logData(String logData);

    <T> void saveData(ArrayList<T> data, String filepath);

    <T> void loadData(ArrayList<T> data, String filepath);

//    ArrayList<ICitizen> getCitizens();
//    
//    public void saveCitizens(ArrayList<ICitizen> list);
//    
//    public ArrayList<ICase> readCases();
}
