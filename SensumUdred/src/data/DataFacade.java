/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import acq.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruger
 */
public class DataFacade implements IData {

    /**
     * Filemanager used for mangeging files
     */
    private FileManager fm;
    /**
     * Datalogger used for manegin logs
     */
    private DataLogger dl;
    /**
     * DBManager used for managing data to the databaser
     */
    private DBManager dbm;

    /**
     * Constructer for datafacade, instantiates an Filemanager, DBManager and a
     * DataLogger
     */
    public DataFacade() {
        fm = new FileManager();
        dbm = new DBManager();
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
    public List<String[]> readUsers() {
        return dbm.loadUsers();
    }

    @Override
    public void logData(String logData) {
        dl.saveLog(logData);
    }

    @Override
    public void logData(String date, String username, String logData) {
        dbm.saveLog(date, username, logData);
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
    @Override
    public <T> void saveData(ArrayList<T> data, String filepath) {
        fm.writeToFile(data, filepath);
    }

    @Override
    public <T> void loadData(ArrayList<T> data, String filepath) {
        fm.readFile(data, filepath);
    }

    @Override
    public void saveUsers(IUser user) {
        dbm.saveUser(user);
    }

    @Override
    public String[] loadUser(String username) {
        return dbm.loadUser(username);
    }

    @Override
    public void deleteUser(IUser user) {
        dbm.deleteUser(user);
    }

    @Override
    public void deleteCase(ICase theCase) {
        dbm.deleteCase(theCase);
    }

    @Override
    public void deleteInquiry(IInquiry inquiry) {
        dbm.deleteInquiry(inquiry);
    }

    @Override
    public boolean hasUniqueUserID(String id) {
        return dbm.hasUniqueUserID(id);
    }

    @Override
    public boolean hasUniqueCitizenID(String id) {
        return dbm.hasUniqueCitizenID(id);
    }

    @Override
    public boolean hasUniqueUsername(String username) {
        return dbm.hasUniqueUsername(username);
    }

    @Override
    public List<String[]> getCitizenData() {
        return dbm.getEverything();
    }

    @Override
    public void saveCase(ICase casen) {
        dbm.saveCase(casen);
    }

    @Override
    public void saveInquiry(IInquiry inquiry) {
        dbm.saveInquiry(inquiry);
    }

    @Override
    public void saveCitizen(ICitizen citizen) {
        dbm.saveCitizen(citizen);
    }

    @Override
    public void updateInquiry(IInquiry inquiry) {
        dbm.updateInquiry(inquiry);
    }

    @Override
    public void updateCase(ICase casen) {
        dbm.updateCase(casen);
    }

    @Override
    public void updateCitizen(ICitizen citizen) {
        dbm.updateCitizen(citizen);
    }

    @Override
    public void deleteCitizen(ICitizen citizen) {
        dbm.deleteCitizen(citizen);
    }

    @Override
    public List<String> getLog() {
        return dbm.getLog();
    }

}
