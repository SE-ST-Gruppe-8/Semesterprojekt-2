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

        try (Connection db = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)){
            Statement st1 = db.createStatement();
            ResultSet rs1 = st1.executeQuery("insert into users values" + data);
            rs1.close();
            st1.close();
        } catch (Exception e) {
            System.out.println(e);
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
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static void main(String[] args) {
        DBManager dbm = new DBManager();
//        IUser u = new SocialWorker("Frederik Fredriksen", "0011223344", "frede", "frede", "frede@gmail.com");
//        dbm.saveUser(u);
//        dbm.loadUsers();
    }

}
