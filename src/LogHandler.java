import java.sql.Timestamp;
import java.io.*;

public class LogHandler {
    private static String logFile = ",/src/logs/log.txt";

    public static void log(String message) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String log = "log: " + message + " | " + timestamp + "\n";
        writeToFile(log);
    }

    public static void error(String message) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String log = "error: " + message + " | " + timestamp + "\n";
        writeToFile(log);
    }

    public static void query(String message) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String log = "query: " + message + " | " + timestamp + "\n";
        writeToFile(log);
    }

    private static void writeToFile(String message) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true));
            writer.append(message);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not write to log file");
        }
    }
}
