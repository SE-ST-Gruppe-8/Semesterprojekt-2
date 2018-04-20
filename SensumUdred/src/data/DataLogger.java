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
}
