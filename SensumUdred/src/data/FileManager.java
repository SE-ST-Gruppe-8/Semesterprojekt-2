package data;

import java.io.File;
import java.io.FileNotFoundException;
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

    Scanner fileReader;
    PrintWriter filePrinter;
    File file;

    public ArrayList<String> readFile(String filePath) {
        file = new File(filePath);
        ArrayList<String> data = new ArrayList<>();
        try {
            fileReader = new Scanner(file);
            fileReader.useDelimiter("\\n");
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

    public void writeToFile(ArrayList<String> data, String filePath) {
        file = new File(filePath);
        try {
            filePrinter = new PrintWriter(file);
            for (String s : data) {
                filePrinter.println(s);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("file NOT foOUDN =oh= my GOD ITS NOT HERE owo");
        } finally {
            filePrinter.close();
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
