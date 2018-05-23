package data;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Robin
 */
public class DataLogger {

    /**
     * Output is a printwiter which sends the output. LogPath is a string which
     * is used as a filepath
     */
    private PrintWriter output;
    private String logPath = "Data.log";

    /**
     * Saves a log
     *
     * @param log Uses a printwriter with a filewriter to write to a file
     */
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
