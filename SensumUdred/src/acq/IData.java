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
    
    /**
     * Returns a string array of CitizenData
     * 
     * @returns Citizendata
     */
    List<String[]> getCitizenData();

    /**
     * Returns a boolean depending on the Users id
     * @param id
     * @returns a boolean
     */
    public boolean hasUniqueUserID(String id);

    /**
     * Returns a boolean depending on the Citizen id
     * @param id
     * @returns a boolean
     */
    public boolean hasUniqueCitizenID(String id);

    /**
     * Returns a boolean depending on the username
     * @param username
     * @returns a boolean
     */
    public boolean hasUniqueUsername(String username);
//    public void saveUsers(ArrayList<IUser> data);
//    
//    public void saveCases(ArrayList<ICase> data);
//
//    public ArrayList<IUser> readUsers();
    /**
     * Returns a string array
     * 
     * @returns a String array 
     */
    public List<String[]> readUsers();
    
    /**
     * Saves User
     * @param user 
     */
    public void saveUsers(IUser user);

    /**
     * Returns a string array of userdata
     * @param username
     * @returns a string array
     */
    public String[] loadUser(String username);

    /**
     * Deletes an User
     * @param user 
     */
    public void deleteUser(IUser user);
    
    /**
     * Deletes an inquiry
     * @param inquiry 
     */
    public void deleteInquiry(IInquiry inquiry);
    
    /**
     * deletes a case
     * @param theCase 
     */
    public void deleteCase(ICase theCase);
    
    /**
     * deletes a citizen
     * @param citizen 
     */
    public void deleteCitizen(ICitizen citizen);

    /** 
     * Logs a string of data
     * @param logData 
     */
    public void logData(String logData);

    /**
     * Logs three strings
     * @param date
     * @param username
     * @param logData 
     */
    public void logData(String date, String username, String logData);

    /**
     * saves an arraylist of data to the filepath
     * @param <T>
     * @param data
     * @param filepath 
     */
    <T> void saveData(ArrayList<T> data, String filepath);

    /**
     * loads data
     * @param <T>
     * @param data
     * @param filepath 
     */
    <T> void loadData(ArrayList<T> data, String filepath);
    
    /**
     * saves a case
     * @param casen 
     */
    void saveCase(ICase casen);
    
    /**
     * saves an Inquiry
     * @param inquiry 
     */
    void saveInquiry(IInquiry inquiry);
    
    /**
     * saves a citizen
     * @param citizen 
     */
    void saveCitizen(ICitizen citizen);
    
    /**
     * updates an Inquiry
     * @param inquiry 
     */
    void updateInquiry(IInquiry inquiry);
    
    /**
     * Updates a case
     * @param casen 
     */
    void updateCase(ICase casen);
    
    /** 
     * Updates a citizen
     * @param citizen 
     */
    void updateCitizen(ICitizen citizen);
    
    /**
     * returns a list of string with log data
     * @returns a list of string
     */
    public List<String> getLog();
    
//    ArrayList<ICitizen> getCitizens();
//    
//    public void saveCitizens(ArrayList<ICitizen> list);
//    
//    public ArrayList<ICase> readCases();
}
