/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import acq.ICase;
import acq.ICitizen;
import acq.IInquiry;
import acq.ISocialWorker;
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
     * Login strings for elephant-sql dbUrl is the Url for the database
     * dbUsername is the username for the database dbPassword is the password
     * for the database.
     */
    //Strings for censor database
    private final String dbUrl = "jdbc:postgresql://horton.elephantsql.com:5432/lulcqfhf";
    private final String dbUsername = "lulcqfhf";
    private final String dbPassword = "aXz0WFAYB4Aw0d5UtTN7WMdc80y_5mZZ";

    //Strings for our database
//    private final String dbUrl = "jdbc:postgresql://horton.elephantsql.com:5432/flugkwex";
//    private final String dbUsername = "flugkwex";
//    private final String dbPassword = "361Fvzl2AmnhDLVqPtBhomyr0-ojDBBC";
        
    /**
     * Saves a user to the database.
     *
     * @param user The user that is going to be saved.
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
            st1.execute("insert into users values" + data);
            st1.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Saves an inquiry to the database.
     *
     * @param inquiry The inquiry that is going to be saved.
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
            st1.execute("insert into inquiries values" + data + "\n"
                    + " insert into hasinquiry values('" + citizenid + "','" + id + "')");
            st1.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Updates an inquiry in the database,
     *
     * @param inquiry The inquiry that is going to be updated.
     */
    public void updateInquiry(IInquiry inquiry) {
        String id = inquiry.getId();
        String description = inquiry.getDescription();
        String informed = Boolean.toString(inquiry.isCitizenInformed());
        String origin = inquiry.getOrigin();
        String citizenid = inquiry.getCitizen().getId();

        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            st1.execute("UPDATE inquiries set inquiryid = '" + id + "', inquirydescription = '"
                    + description + "', iscitizeninformed = '"
                    + informed + "', origin = '" + origin + "' where inquiryid = '" + id + "';");
            st1.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Updates a citizen in the database.
     *
     * @param citizen The citizen that is going to be updated
     */
    public void updateCitizen(ICitizen citizen) {
        String id = citizen.getId();
        String name = citizen.getName();
        String needs = citizen.getNeeds();

        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            st1.execute("UPDATE citizens set needs = '" + needs + "' where citizenId = '" + id + "';\n");
            st1.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Updates a case in the database.
     *
     * @param casen The case that is going to be updated
     */
    public void updateCase(ICase casen) {
        String id = casen.getID();
        String description = casen.getDescription();
        String process = casen.getProcess();
        String citizenid = casen.getCitizen().getId();

        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            st1.execute("UPDATE cases set caseid = '" + id + "', casedescription = '"
                    + description + "', process = '" + process + "' where caseid = '" + id + "';");
            st1.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Returns a list of String[] containing citizens & their inquiries, cases
     * and the social worker assigned to the case.
     *
     * @return A list of String[], where every String[] contains data about a
     * citizen & their inquiry + case
     */
    public List<String[]> getEverything() {
        String[] columns = {"citizenid", "citizenname", "needs", "inquiryid", "inquirydescription", "iscitizeninformed", "origin",
            "caseid", "casedescription", "process", "userid", "name", "mail", "username", "password", "role"};
        ArrayList<String[]> data = new ArrayList<>();

        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            ResultSet rs1 = st1.executeQuery("SELECT citizens.*, inquiries.*, cases.*,users.*\n"
                    + "FROM citizens\n"
                    + "LEFT JOIN inquiries ON inquiries.inquiryid = (SELECT inquiryid FROM hasinquiry WHERE citizenid = citizens.citizenid)\n"
                    + "LEFT JOIN cases ON cases.caseid = (SELECT caseid FROM hascase WHERE citizenid = citizens.citizenid)"
                    + "LEFT JOIN users ON users.userid = (SELECT userid FROM createdby WHERE caseid = cases.caseid)");
            while (rs1.next()) {
                String[] s = new String[16];
                for (int i = 0; i < s.length; i++) {
                    s[i] = rs1.getString(columns[i]);
                }
                data.add(s);
            }
            rs1.close();
            st1.close();
            return data;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Saves a case to the database.
     *
     * @param casen The case that is going to be saved.
     *
     */
    public void saveCase(ICase casen) {
        ISocialWorker sw = casen.getSocialWorker();
        String id = casen.getID();
        String description = casen.getDescription();
        String process = casen.getProcess();
        String citizenid = casen.getCitizen().getId();
        String data = "('" + id + "','" + description + "','" + process + "');";
        String data2 = "('" + sw.getID() + "','" + id + "');";

        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            st1.execute("insert into cases values" + data + "\n"
                    + "insert into hascase values('" + citizenid + "','" + id + "');\n"
                    + "insert into createdby values('" + sw.getID() + "','" + id + "');");
            st1.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Saves a citizen to the database.
     *
     * @param citizen The citizen that is going to be saved
     */
    public void saveCitizen(ICitizen citizen) {
        String id = citizen.getId();
        String name = citizen.getName();
        String needs = citizen.getNeeds();
        String data = "('" + id + "','" + name + "','" + needs + "');";

        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            st1.execute("insert into citizens values" + data);
            st1.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Loads a List of String[] of users from the database.
     *
     * @return A List of String[]. Each String[] contains the data for one user
     */
    public List<String[]> loadUsers() {
        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            ResultSet rs1 = st1.executeQuery("select * from \"public\".\"users\"");
            List<String[]> list = new ArrayList<>();
            while (rs1.next()) {
                String[] array = new String[6];
                array[0] = rs1.getString("name");
                array[1] = rs1.getString("userId");
                array[2] = rs1.getString("username");
                array[3] = rs1.getString("password");
                array[4] = rs1.getString("mail");
                array[5] = rs1.getString("role");
                list.add(array);
            }
            rs1.close();
            st1.close();
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Loads a single user from the database
     *
     * @param username The username used to search for a user in the database
     * @return a String[] containing the information of a single user
     */
    public String[] loadUser(String username) {
        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            ResultSet rs1 = st1.executeQuery("select * FROM \"public\".\"users\" where username = '" + username + "'");
            String[] array = new String[6];
            while (rs1.next()) {
                array[0] = rs1.getString("name");
                array[1] = rs1.getString("userId");
                array[2] = rs1.getString("username");
                array[3] = rs1.getString("password");
                array[4] = rs1.getString("mail");
                array[5] = rs1.getString("role");
            }
            rs1.close();
            st1.close();
            return array;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Deletes a user from the database.
     *
     * @param user The user that is going to be deleted
     */
    public void deleteUser(IUser user) {
        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            st1.execute("delete from users where username ='" + user.getUsername() + "'");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Deletes an inquiry from the database.
     *
     * @param inquiry The inquiry that is going to be deleted.
     */
    public void deleteInquiry(IInquiry inquiry) {
        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            st1.execute("delete from \"public\".\"inquiries\" where inquiryId ='" + inquiry.getId() + "'");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Deletes a case from the database.
     *
     * @param theCase The case that is going to be deleted.
     */
    public void deleteCase(ICase theCase) {
        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            st1.execute("delete from \"public\".\"cases\" where caseId ='" + theCase.getID() + "'");

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
    }

    /**
     * Deletes a citizen from the database.
     *
     * @param citizen The citizen that is going to be deleted.
     */
    public void deleteCitizen(ICitizen citizen) {
        System.out.println(citizen.getId());
        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            st1.execute("delete from \"public\".\"citizens\" where citizenId ='" + citizen.getId() + "'");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Checks whether or not a given id already exists in the database.
     *
     * @param id That is to be checked if unique.
     * @return a boolean indicating whether or not the given id is unique
     */
    public boolean hasUniqueUserID(String id) {
        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            ResultSet rs1 = st1.executeQuery("select COUNT(userId) FROM \"public\".\"users\" where userId = '" + id + "'");
            rs1.next();
            if (rs1.getInt("count") == 1) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Checks whether or not a given citizen id already exists in the database.
     *
     * @param id That is to be checked if unique
     * @return a boolean indicating whether or not the given id is unique
     */
    public boolean hasUniqueCitizenID(String id) {
        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            ResultSet rs1 = st1.executeQuery("select COUNT(citizenId) FROM \"public\".\"citizens\" where citizenId = '" + id + "'");
            rs1.next();
            if (rs1.getInt("count") == 1) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Checks whether or not a given username is unique.
     *
     * @param username That is to be checked if unique.
     * @return a boolean that indicates whether or not the given username is unique.
     *
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
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Saves logged data, along with date & user, to the database.
     * 
     * @param date The date of the log.
     * @param username The name of the log.
     * @param logData The data of the log.
     */
    public void saveLog(String date, String username, String logData) {
        String data = "('" + date + "','" + username + "','" + logData + "');";

        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            st1.execute("insert into logdata values" + data);
            st1.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Retrieves all logged data from the database.
     * 
     * @return a list of String[]. Each String[] contains information about one log
     */
    public List<String> getLog() {
        ArrayList<String> list = new ArrayList();

        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            ResultSet rs1 = st1.executeQuery("select * from logdata");
            while (rs1.next()) {

                String s = "";
                s = String.format("%-32s %-20s %-220s", rs1.getString("datelogged"), rs1.getString("username"), rs1.getString("dataline"));
//                s = rs1.getString("datelogged") + " " + rs1.getString("username") + " " + rs1.getString("dataline");
                list.add(s);
            }
            rs1.close();
            st1.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
