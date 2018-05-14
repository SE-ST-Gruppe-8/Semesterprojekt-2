/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import acq.*;
import java.util.ArrayList;

/**
 *
 * @author Bruger
 */
public class DataFacade implements IData {

    private FileManager fm;
    private DataLogger dl;



    public DataFacade() {
        fm = new FileManager();
        dl = new DataLogger();
    }

//    @Override
//    public ArrayList<IUser> readUsers() {
//        return new ArrayList<IUser>();
//    }
//
//    @Override
//    public void saveUsers(ArrayList<IUser> data) {
//        
//    }
    @Override
    public void logData(String logData) {
        dl.saveLog(logData);
    }

//    @Override
//    public ArrayList<ICitizen> getCitizens() {
//        return fm.readCitizens();
//    }
//
//    @Override
//    public void saveCitizens(ArrayList<ICitizen> list) {
//        fm.saveCitizens(list);
//    }
//    public void saveCases(ArrayList<ICase> data) {
//        fm.saveCases(data);
//    }
//    
//    @Override
//    public ArrayList<ICase> readCases() {
//        return fm.readCases();
//    }
    public <T> void saveData(ArrayList<T> data, String filepath) {
        fm.writeToFile(data, filepath);
    }

    @Override
    public <T> void loadData(ArrayList<T> data, String filepath) {
        fm.readFile(data, filepath);

    }

    @Override
    public boolean hasUniqueUserUD(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasUniqueCitizenID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
