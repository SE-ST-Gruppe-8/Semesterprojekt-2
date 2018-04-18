package data;

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

    ObjectOutputStream fileReader;
    ObjectOutputStream fileWriter;
    File file;

    public ArrayList<String> readFile(String filePath) {
        file = new File(filePath);
        ArrayList<IUser> data = new ArrayList<>();
        try {
            fileReader = new FileInputStream(file);
            while (fileReader.hasNext()) {
                data.add(fileReader.next());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found :( owo *starts twerking*");
        } finally {
            fileReader.close();
        }
        return data;
    }

    public void writeToFile(ArrayList<IUser> data, String filePath) {
        file = new File(filePath);
        try {
            fileWriter = new ObjectOutputStream(new FileOutputStream(file));
            for (IUser u : data) {
                fileWriter.writeObject(IUser);
            }
            fileWriter.close();
        } catch (FileNotFoundException ex) {
            System.out.println("file NOT foOUDN =oh= my GOD ITS NOT HERE owo");
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    public static void main(String[] args) {
        ArrayList<String> test = new ArrayList<>();
        test.add("shit");
        test.add("boop");
        test.add("3");
        FileManager fm = new FileManager();
        fm.writeToFile(test, "ooba.txt");
        for(String s : fm.readFile("ooba.txt")) {
            System.out.println(s);
        }
    }

}
