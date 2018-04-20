package data;

import acq.IUser;
import business.SystemAdmin;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author J
 */
public class FileManager {

    ObjectInputStream fileReader;
    ObjectOutputStream fileWriter;
    File file;

    public IUser readFile(String filePath) {
        file = new File(filePath);
        IUser data = new IUser() {
            @Override
            public String getName() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        try {
            fileReader = new ObjectInputStream(new FileInputStream(file));
            data = (IUser) fileReader.readObject();
            } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public void writeToFile(IUser data, String filePath) {
        file = new File(filePath);
        try {
            fileWriter = new ObjectOutputStream(new FileOutputStream(file));
                System.out.println("øf");
                fileWriter.writeObject(data);
            fileWriter.close();
        } catch (FileNotFoundException ex) {
            System.out.println("file NOT foOUDN =oh= my GOD ITS NOT HERE owo");
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    public static void main(String[] args) {
        ArrayList<IUser> test = new ArrayList<>();
        FileManager fm = new FileManager();
        IUser u = new SystemAdmin("ASS","b","c","d","e");
        fm.writeToFile(u, "awoo.dat");
        System.out.println(fm.readFile("awoo.dat").getName());
        
        
    }

}
