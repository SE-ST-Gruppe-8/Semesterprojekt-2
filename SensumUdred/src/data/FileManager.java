package data;

import acq.IUser;
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
                    System.out.println("poo");
                    IUser u = (IUser) fileReader.readObject();
                    data.add(u);
                } catch (EOFException eof) {
                    System.out.println("Reached end of file.");
                    break; // stop reading
                }
            }

        } catch (IOException ex) {
            System.out.println("IOException encountered.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found.");
        }
        return data;
    }

    public void writeToFile(ArrayList<IUser> data) {
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

    public static void main(String[] args) {
        ArrayList<IUser> test = new ArrayList<>();
        FileManager fm = new FileManager();
        test.add(new SystemAdmin("ASS", "b", "c", "d", "e"));
        test.add(new SystemAdmin("AssSS", "b", "c", "d", "e"));
        test.add(new SystemAdmin("AssSS", "b", "c", "d", "e"));
        test.add(new SystemAdmin("AssSS", "b", "c", "d", "e"));
        test.add(new SystemAdmin("AssSS", "b", "c", "d", "e"));
        test.add(new SystemAdmin("AssSS", "b", "c", "d", "e"));
        test.add(new SystemAdmin("AssSS", "b", "c", "d", "e"));
        test.add(new SystemAdmin("AssSS", "b", "c", "d", "e"));
        test.add(new SystemAdmin("AssSS", "b", "c", "d", "e"));
        test.add(new SystemAdmin("AssSS", "b", "c", "d", "e"));
        test.add(new SystemAdmin("AssSS", "b", "c", "d", "e"));

        fm.writeToFile(test);
        System.out.print(fm.readFile());

    }

}
