package sh.bash.log;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class LogProcessor {
    /**
     * Created by anmulholland on 5/11/14.
     * <p>
     * This is a simple program to parse access logs and return some useful stats on them
     * In typical use, a daily access log gets to about 1GB per server for us, hence some care
     * has been taken to ensuring we can process data quickly.
     * Right now we need to update a config.properties to change the path to the log which is a little
     * clunky.
     */

    public static void main(String args[]) {

        Properties properties = new Properties();
        InputStream configFile;
        String logPath = "";
        String csvFile = "";

        try {
            configFile = new FileInputStream("config.properties");

            properties.load(configFile);
            logPath = properties.getProperty("logPath");
            csvFile = properties.getProperty("csvFile");

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
            System.exit(255);
        }

        GroupFields groupFields = new GroupFields();

        LogReader logReader = new LogReader();
        logReader.read(logPath, groupFields::add);

        Path csvPath = Paths.get(csvFile);
        try (FileWriter fileWriter = new FileWriter(csvPath.toFile())) {
            PrintWriter csvWriter = new PrintWriter(fileWriter);
            CsvReport csvReport = new CsvReport();
            csvReport.generate(groupFields.getGroupFields(), csvWriter);
        } catch (IOException e) {
            System.err.format("IOException: %s%n ", e);
        }


    }
}
