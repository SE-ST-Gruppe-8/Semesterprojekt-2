package data;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Robin
 */
public class DataLogger {

    private PrintWriter output;
    private String logPath = "Data.log";

    public void saveLog(String log) {
        try {
            output = new PrintWriter(new FileWriter(logPath, true));
            output.println(log);
            output.flush();
        } catch (IOException ex) {
            System.out.println("could not log data");
        } finally {
            output.close();
        }

    }

//    public static void main(String[] args) {
//        DataLogger dl = new DataLogger();
//        dl.saveLog("test1");
//        dl.saveLog("test2");
//        dl.saveLog("test3");
//        dl.saveLog("test4");
//        dl.saveLog(new Date().toString());
//    }
}
