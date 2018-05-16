/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import acq.IUser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import org.postgresql.util.PSQLException;

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

    public static void main(String[] args) {
//        DBManager dbm = new DBManager();
//        IUser u = new SocialWorker("Frederik Fredriksen", "0011223344", "frede", "frede", "frede@gmail.com");
//        dbm.saveUser(u);
//        dbm.loadUsers();
//        String id = "0";
//        dbm.hasUniqueUserID(id);
    }

}
