/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import acq.ICase;
import acq.ICitizen;
import acq.IInquiry;
import acq.IUser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Frederik
 */
public class DBManager {

    /**
     * Loging strings for elephant-sql
     * dbUrl is the Url for the database
     * dbUsername is the username for the database
     * dbPassword is the password for the database
     */
    private String dbUrl = "jdbc:postgresql://horton.elephantsql.com:5432/flugkwex";
    private String dbUsername = "flugkwex";
    private String dbPassword = "361Fvzl2AmnhDLVqPtBhomyr0-ojDBBC";

    /**
     * saveUser is saving an User
     * @param user 
     * Opens an connection to the database and sends a quary with a resultsset
     * The quary inserts the users values into the users entity set
     * 
     */
    public void saveUser(IUser user) {
        String id = user.getID();
        String name = user.getName();
        String mail = user.getEmail();
        String username = user.getUsername();
        String password = user.getPassword();
        int role = user.getRole();
        String data = "('" + id + "','" + name + "','" + mail + "','" + username + "','" + password + "','" + role + "');";

        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            ResultSet rs1 = st1.executeQuery("insert into users values" + data);
            rs1.close();
            st1.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    /**
     * saves an inquiry
     * @param inquiry 
     * Opens an connection to the database and sends a quary with a resultsset
     * The quary inserts the inquiry values into the entityset inquiries and also
     * inserts the inquiryid into the relation hasinqury
     */
    
    public void saveInquiry(IInquiry inquiry) {
        String id = inquiry.getId();
        String description = inquiry.getDescription();
        String informed = Boolean.toString(inquiry.isCitizenInformed());
        String origin = inquiry.getOrigin();
        String citizenid = inquiry.getCitizen().getId();
        String data = "('" + id + "','" + description + "','" + informed + "','" + origin + "');";

        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            ResultSet rs1 = st1.executeQuery("insert into inquiries values" + data + "\n"
                    + " insert into hasinquiry values('" + citizenid + "','" + id + "')");
            rs1.close();
            st1.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    /**
     * Updates an inquiry
     * @param inquiry 
     * Opens an connection to the database and sends a quary with a resultsset
     * The quary updates the inquiry values set on the entityset inquiries where the
     * inquiryid is equals to the inquiryid in inquiries
     */
    public void updateInquiry(IInquiry inquiry) {
        String id = inquiry.getId();
        String description = inquiry.getDescription();
        String informed = Boolean.toString(inquiry.isCitizenInformed());
        String origin = inquiry.getOrigin();
        String citizenid = inquiry.getCitizen().getId();

        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            ResultSet rs1 = st1.executeQuery("UPDATE inquiries set inquiryid = '" + id + "', inquirydescription = '"
                    + description + "', iscitizeninformed = '"
                    + informed + "', origin = '" + origin + "' where inquiryid = '" + id + "';");
            rs1.close();
            st1.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    /**
     * Opens an connection to the database and sends a quary with a resultsset
     * The quary updates the citizen values set on the entityset citizens where 
     * the citizenid is equals to the id in citizens
     * @param citizen 
     */
    public void updateCitizen(ICitizen citizen) {
        String id = citizen.getId();
        String name = citizen.getName();
        String needs = citizen.getNeeds();

        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            ResultSet rs1 = st1.executeQuery("UPDATE citizens set needs = '" + needs + "' where id = '" + id + "';\n");
            rs1.close();
            st1.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    /**
     * Opens an connection to the database and sends a quary with a resultsset
     * The quary updates the case values set on the entityset cases where 
     * the casesid is equals to the casesid in cases
     * @param citizen 
     */
    public void updateCase(ICase casen) {
        String id = casen.getId();
        String description = casen.getDescription();
        String process = casen.getProcess();
        String citizenid = casen.getCitizen().getId();

        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            ResultSet rs1 = st1.executeQuery("UPDATE cases set caseid = '" + id + "', casedescription = '"
                    + description + "', process = '" + process + "' where caseid = '" + id + "';\n"
                    + " Update hascase set citizenid = '" + citizenid + "',  caseid = '" + id
                    + "' where caseid = '" + id + "';");
            rs1.close();
            st1.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    /**
     * Returns A list of String[] containing citizens & their inquiries + cases.
     * 
     * @return A list of String[], where every String[] contains data about a citizen & their inquiry + case
     */
    public List<String[]> getEverything() {
        String[] columns = {"id", "name", "needs", "inquiryid", "inquirydescription", "iscitizeninformed", "origin",
            "caseid", "casedescription", "process"};
        ArrayList<String[]> data = new ArrayList<>();

        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            ResultSet rs1 = st1.executeQuery("SELECT citizens.*, inquiries.*, cases.*\n"
                    + "FROM citizens\n"
                    + "LEFT JOIN inquiries ON inquiries.inquiryid = (SELECT inquiryid FROM hasinquiry WHERE citizenid = citizens.id)\n"
                    + "LEFT JOIN cases ON cases.caseid = (SELECT caseid FROM hascase WHERE citizenid = citizens.id)"
                    + "");
            while (rs1.next()) {
                String[] s = new String[10];
                for (int i = 0; i < s.length; i++) {
                    s[i] = rs1.getString(columns[i]);
                }
                data.add(s);
            }
            rs1.close();
            st1.close();
            return data;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    /**
     * saves a case
    * @param casen
    * Opens an connection to the database and sends a quary with a resultsset
    * The quary updates inserts the case data into the entity set cases and also
    * inserts the caseid into the relation hascase where with its citizenid.
    */
    public void saveCase(ICase casen) {
        String id = casen.getId();
        String description = casen.getDescription();
        String process = casen.getProcess();
        String citizenid = casen.getCitizen().getId();
        String data = "('" + id + "','" + description + "','" + process + "');";

        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            ResultSet rs1 = st1.executeQuery("insert into cases values" + data + "\n"
                    + "insert into hascase values('" + citizenid + "','" + id + "')");
            rs1.close();
            st1.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    /**
     * Opens an connection to the database and sends a quary with a resultsset
     * The quary inserts the citizen values into the entityset citizens 
     * @param citizen 
     */
    public void saveCitizen(ICitizen citizen) {
        String id = citizen.getId();
        String name = citizen.getName();
        String needs = citizen.getNeeds();
        String data = "('" + id + "','" + name + "','" + needs + "');";

        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            ResultSet rs1 = st1.executeQuery("insert into citizens values" + data);
            rs1.close();
            st1.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

/**
    * Returns a list of users
    * Opens an connection to the database and sends a quary with a resultsset
    * The quary selects all tuples from the entityset users 
    * The values from each column is put into an array of string which is added to
    * an arraylist
    * @return a list of strings
    */
    public List<String[]> loadUsers() {
        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            ResultSet rs1 = st1.executeQuery("select * from \"public\".\"users\"");
            List<String[]> list = new ArrayList<>();
            while (rs1.next()) {
                String[] array = new String[6];
                array[0] = rs1.getString("name");
                array[1] = rs1.getString("id");
                array[2] = rs1.getString("username");
                array[3] = rs1.getString("password");
                array[4] = rs1.getString("mail");
                array[5] = rs1.getString("role");
                list.add(array);
            }
            rs1.close();
            st1.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
/*
     * Opens an connection to the database and sends a quary with a resultsset
    * The quary selects all tuples from the entityset users 
    * The values from each column is put into an array of string which is added to
    * an arraylist
    */
    public String[] loadUser(String username) {
        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            ResultSet rs1 = st1.executeQuery("select * FROM \"public\".\"users\" where username = '" + username + "'");
            String[] array = new String[6];
            while (rs1.next()) {
                array[0] = rs1.getString("name");
                array[1] = rs1.getString("id");
                array[2] = rs1.getString("username");
                array[3] = rs1.getString("password");
                array[4] = rs1.getString("mail");
                array[5] = rs1.getString("role");
            }
            rs1.close();
            st1.close();
            return array;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    /**
     * deletes an user
     * Opens an connection to the database and sends a quary with a resultsset
     * The quary deletes an tuple from the entityset users where the user 
     * username is equals to users.username
     * @param user 
     */
    public void deleteUser(IUser user) {
        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            ResultSet rs1 = st1.executeQuery("delete from \"public\".\"users\" where username ='" + user.getUsername() + "'");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    /**
     * deletes an inquiry
     * @param inquiry 
     * Opens an connection to the database and sends a quary with a resultsset
     * The quary deletes an tuple from the entityset inquries where the inquirys 
     * id is equals to inquiries.inquiryid
     */
    public void deleteInquiry(IInquiry inquiry) {
        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            ResultSet rs1 = st1.executeQuery("delete from \"public\".\"inquiries\" where inquiryid ='" + inquiry.getId() + "'");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    /**
    * Deletes a case
    * @param theCase
    * Opens an connection to the database and sends a quary with a resultsset
    * The quary deletes an tuple from the entityset cases where the caseid 
    * is equal to cases.id
    */
    public void deleteCase(ICase theCase) {
        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            ResultSet rs1 = st1.executeQuery("delete from \"public\".\"cases\" where caseid ='" + theCase.getId() + "'");

        } catch (Exception ex) {
            System.out.println(ex);

        }
    }
    /**
    * Deletes a case
    * @param citizen
    * Opens an connection to the database and sends a quary with a resultsset
    * The quary deletes an tuple from the entityset citizens where the citizenid 
    * is equal to citizens.id
    */
    public void deleteCitizen(ICitizen citizen) {
        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            ResultSet rs1 = st1.executeQuery("delete from \"public\".\"citizens\" where id ='" + citizen.getId() + "'");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    /**
     * Returns a boolean depending on the user id
     * @param id
     * Opens an connection to the database and sends a quary with a resultsset
     * The quary counts how many ids in the entityset users which are equal to
     * id. If any of these are equal to it, it returns false.
     * @returns a boolean
     */
    public boolean hasUniqueUserID(String id) {
        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            ResultSet rs1 = st1.executeQuery("select COUNT(id) FROM \"public\".\"users\" where id = '" + id + "'");
            rs1.next();
            if (rs1.getInt("count") == 1) {
                return false;
            } else {
                return true;
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }
    /**
     * Returns a boolean depending on the user id
     * @param id
     * Opens an connection to the database and sends a quary with a resultsset
     * The quary counts how many ids in the entityset cittizens which are equal
     * to id. If any of these are equal to it, it returns false.
     * @returns a boolean
     */
    public boolean hasUniqueCitizenID(String id) {
        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            ResultSet rs1 = st1.executeQuery("select COUNT(id) FROM \"public\".\"citizens\" where id = '" + id + "'");
            rs1.next();
            if (rs1.getInt("count") == 1) {
                return false;
            } else {
                return true;
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }
    /**
    * Returns a boolean depending on the user id
    * @param id
    * Opens an connection to the database and sends a quary with a resultsset
    * The quary counts how many usernames in the entityset users which are equal
    * to username. If any of these are equal to it, it returns false.
    * @returns a boolean
    */
    public boolean hasUniqueUsername(String username) {
        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            ResultSet rs1 = st1.executeQuery("select COUNT(username) FROM \"public\".\"users\" where username = '" + username + "'");
            rs1.next();
            if (rs1.getInt("count") == 1) {
                return false;
            }
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }
    /**
     * Saves the log data into the database
     * @param date
     * @param username
     * @param logData 
     * Opens an connection to the database and sends a quary with a resultsset
     * The quary inserts the string data into the entityset logdata
     */
    public void saveLog(String date, String username, String logData) {
        String data = "('" + date + "','" + username + "','" + logData + "');";

        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            ResultSet rs1 = st1.executeQuery("insert into logdata values" + data);
            rs1.close();
            st1.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    /**
     * Returns a list of logdata
     * Opens an connection to the database and sends a quary with a resultsset
     * Selects all tuples from the entityset logdata
     * Formats the string and adds it a list
     * @returns a list of Strings
     */
    public List<String> getLog() {
        ArrayList<String> list = new ArrayList();

        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            ResultSet rs1 = st1.executeQuery("select * from logdata");
            while (rs1.next()) {

                String s = "";
                s = String.format("%-32s %-20s %-220s",rs1.getString("datelogged"), rs1.getString("username"), rs1.getString("dataline"));
//                s = rs1.getString("datelogged") + " " + rs1.getString("username") + " " + rs1.getString("dataline");
                list.add(s);
            }
            rs1.close();
            st1.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }
}
