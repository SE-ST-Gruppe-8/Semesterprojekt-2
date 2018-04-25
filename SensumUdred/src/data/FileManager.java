package data;

import acq.IUser;
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

    public <T> ArrayList<T> readFile(ArrayList<T> data, String filepath) {
        file = new File(filepath + ".dat");
        
        boolean read = true;
        try {
            fileReader = new ObjectInputStream(new FileInputStream(file));
            while (read) {
                try {
                    T t = (T) fileReader.readObject();
                    data.add(t);
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

    public <T> void writeToFile(ArrayList<T> data, String filepath) {
        System.out.println(data);
        file = new File(filepath + ".dat");
        try {
            fileWriter = new ObjectOutputStream(new FileOutputStream(file));
            for (T t : data) {
                fileWriter.writeObject(t);
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
        test.add(new SystemAdmin("ASS", "b", "starts", "twerking", "e"));
        test.add(new SocialWorker("brrrSS", "b", "starts", "twerking", "e"));
        fm.writeToFile(test, "users");
        test = new ArrayList<>();
        fm.readFile(test, "users");
        System.out.println(test);

    }

}
