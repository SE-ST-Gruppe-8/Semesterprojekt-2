package data;

import acq.ICase;
import acq.ICitizen;
import acq.IUser;
import business.Case;
import business.Citizen;
import business.Inquiry;
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

//    public ArrayList<ICase> readCases() {
//        file = new File("cases.dat");
//        ArrayList<ICase> data = new ArrayList<>();
//        boolean read = true;
//        try {
//            fileReader = new ObjectInputStream(new FileInputStream(file));
//            while (read) {
//                try {
//                    ICase c = (ICase) fileReader.readObject();
//                    data.add(c);
//                } catch (EOFException eof) {
//                    System.out.println("Reached end of file.");
//                    break; // stop reading
//                }
//            }
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        } catch (ClassNotFoundException ex) {
//            System.out.println("Class not found.");
//        }
//        return data;
//    }
//    public ArrayList<ICitizen> readCitizens() {
//        file = new File("citizens.dat");
//        ArrayList<ICitizen> data = new ArrayList<>();
//        boolean read = true;
//            try {
//                fileReader = new ObjectInputStream(new FileInputStream(file));
//                while (read) {
//                    try {
//                        ICitizen c = (ICitizen) fileReader.readObject();
//                    data.add(c);
//                } catch (EOFException eof) {
//                    System.out.println("Reached end of file.");
//                    break; // stop reading
//                }
//            }
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        } catch (ClassNotFoundException ex) {
//            System.out.println("Class not found.");
//        }
//        return data;
//    }
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

//    public void saveCitizens(ArrayList<ICitizen> data) {
//        System.out.println(data);
//        file = new File("citizens.dat");
//        try {
//            fileWriter = new ObjectOutputStream(new FileOutputStream(file));
//            for (ICitizen c : data) {
//                fileWriter.writeObject(c);
//            }
//            fileWriter.close();
//        } catch (FileNotFoundException ex) {
//            System.out.println("File not found.");
//        } catch (IOException ex) {
//            System.out.println("IOException encountered.");
//        }
//
//    }
//    public void writeCaseToFile(ArrayList<ICase> data) {
//        System.out.println(data);
//        file = new File("cases.dat");
//        try {
//            fileWriter = new ObjectOutputStream(new FileOutputStream(file));
//            for (ICase u : data) {
//                fileWriter.writeObject(u);
//            }
//            fileWriter.close();
//        } catch (FileNotFoundException ex) {
//            System.out.println("File not found.");
//        } catch (IOException ex) {
//            System.out.println("IOException encountered.");
//        }
//    }
//    public void saveCases(ArrayList<ICase> data) {
//        System.out.println(data);
//        file = new File("cases.dat");
//        try {
//            fileWriter = new ObjectOutputStream(new FileOutputStream(file));
//            for (ICase c : data) {
//                fileWriter.writeObject(c);
//            }
//            fileWriter.close();
//        } catch (FileNotFoundException ex) {
//            System.out.println("File not found.");
//        } catch (IOException ex) {
//            System.out.println("IOException encountered.");
//        }
//    }
    public static void main(String[] args) {
        ArrayList<IUser> test = new ArrayList<>();
        ArrayList<ICase> test2 = new ArrayList<>();
        ArrayList<ICitizen> test3 = new ArrayList<>();
        FileManager fm = new FileManager();
        test.add(new SystemAdmin("ASS", "b", "starts", "twerking", "e"));
        SocialWorker sw = new SocialWorker("polse", "joe", "polse123", "lol", "twerk");
        Citizen c = new Citizen("name", "id","needs");
        Inquiry i = new Inquiry("id","origin",false,c, "description");
        c.setInquiry(i);

        
        test.add(sw);
        test2.add(new Case("joe", "pind", "lol", sw, c));
        test3.add(c);
        fm.writeToFile(test, "users");
        fm.writeToFile(test2, "cases");
        fm.writeToFile(test3, "citizens");

        
    }

}
