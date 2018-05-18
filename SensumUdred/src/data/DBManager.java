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
import business.Citizen;
import business.Inquiry;
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

    //Login to elephant-sql
    private String dbUrl = "jdbc:postgresql://horton.elephantsql.com:5432/flugkwex";
    private String dbUsername = "flugkwex";
    private String dbPassword = "361Fvzl2AmnhDLVqPtBhomyr0-ojDBBC";

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
                + informed + "', origin = '" + origin + "' where inquiryid = '" + id + "';\n" 
                + " Update hasinquiry set citizenid = '" + citizenid + "',  inquiryid = '" + id
                + "' where inquiryid = '" + id + "';");
            rs1.close();
            st1.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
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

    public List<String[]> getEverything() {
        String[] columns = {"id", "name", "needs", "inquiryid", "inquirydescription", "iscitizeninformed", "origin",
            "caseid", "casedescription", "process"};
        ArrayList<String[]> data = new ArrayList<>();

        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            ResultSet rs1 = st1.executeQuery("SELECT citizens.*, inquiries.*, cases.*\n"
                    + "FROM citizens\n"
                    + "LEFT JOIN inquiries ON inquiries.inquiryid = (SELECT inquiryid FROM hasinquiry WHERE citizenid = citizens.id)\n"
                    + "LEFT JOIN cases ON cases.caseid = (SELECT caseid FROM hascase WHERE citizenid = citizens.id)");
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

    public boolean deleteUser(IUser user) {
        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            ResultSet rs1 = st1.executeQuery("delete from \"public\".\"users\" where username ='" + user.getUsername() + "'");
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    public boolean deleteInquiry(IInquiry inquiry) {
        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            ResultSet rs1 = st1.executeQuery("delete from \"public\".\"inquiries\" where inquiryid ='" + inquiry.getId() + "'");
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    public boolean deleteCase(ICase theCase) {
        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement st1 = db.createStatement();
            ResultSet rs1 = st1.executeQuery("delete from \"public\".\"cases\" where caseid ='" + theCase.getId() + "'");
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

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

    public static void main(String[] args) {
        DBManager dbm = new DBManager();
//        IUser u = new SocialWorker("Frederik Fredriksen", "0011223344", "frede", "frede", "frede@gmail.com");
//        dbm.saveUser(u);
//        dbm.loadUsers();
//        String id = "0";
//        dbm.hasUniqueUserID(id);
    }

}
