package data;

import acq.ICase;
import acq.IUser;
import business.Case;
import business.Citizen;
import business.SocialWorker;
import business.SystemAdmin;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author J
 */
public class FileManager {

    ObjectInputStream fileReader;
    ObjectOutputStream fileWriter;
    File file;

    public ArrayList<IUser> readFile() {
        file = new File("users.dat");
        ArrayList<IUser> data = new ArrayList<>();
        boolean read = true;
        try {
            fileReader = new ObjectInputStream(new FileInputStream(file));
            while (read) {
                try {
                    IUser u = (IUser) fileReader.readObject();
                    data.add(u);
                } catch (EOFException eof) {
                    System.out.println("Reached end of file.");
                    break; // stop reading
                }
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found.");
        }
        return data;
    }

    public ArrayList<ICase> readCases() {
        file = new File("cases.dat");
        ArrayList<ICase> data = new ArrayList<>();
        boolean read = true;
        try {
            fileReader = new ObjectInputStream(new FileInputStream(file));
            while (read) {
                try {
                    ICase c = (ICase) fileReader.readObject();
                    data.add(c);
                } catch (EOFException eof) {
                    System.out.println("Reached end of file.");
                    break; // stop reading
                }
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found.");
        }
        return data;
    }

    public void writeToFile(ArrayList<IUser> data) {
        System.out.println(data);
        file = new File("users.dat");
        try {
            fileWriter = new ObjectOutputStream(new FileOutputStream(file));
            for (IUser u : data) {
                fileWriter.writeObject(u);
            }
            fileWriter.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        } catch (IOException ex) {
            System.out.println("IOException encountered.");
        }
    }
    public void writeCaseToFile(ArrayList<ICase> data) {
        System.out.println(data);
        file = new File("cases.dat");
        try {
            fileWriter = new ObjectOutputStream(new FileOutputStream(file));
            for (ICase u : data) {
                fileWriter.writeObject(u);
            }
            fileWriter.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        } catch (IOException ex) {
            System.out.println("IOException encountered.");
        }

    }

    public void saveCases(ArrayList<ICase> data) {
        System.out.println(data);
        file = new File("cases.dat");
        try {
            fileWriter = new ObjectOutputStream(new FileOutputStream(file));
            for (ICase c : data) {
                fileWriter.writeObject(c);
            }
            fileWriter.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        } catch (IOException ex) {
            System.out.println("IOException encountered.");
        }

    }

    public static void main(String[] args) {
        ArrayList<IUser> test = new ArrayList<>();
        
        ArrayList<ICase> test2 = new ArrayList<>();
        FileManager fm = new FileManager();
        test.add(new SystemAdmin("ASS", "b", "starts", "twerking", "e"));
        SocialWorker sw = new SocialWorker( "polse","joe","polse123","lol","twerk");
        test.add(sw);
        test2.add( new Case("joe","pik","lol",sw,new Citizen()));
        
        

        fm.writeToFile(test);
        fm.writeCaseToFile(test2);
        
        System.out.println(fm.readFile());

    }

}
