package sh.bash.log;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/* LogReader read lines from a file and returns it as a List.
 * This has a side effect of resulting in the whole line being
 * read into memory, which is not ideal.
 *  */

public class LogReader {
    public List<String> read(String logPath) {
        String readLine;
        Path readPath = Paths.get(logPath);
        List<String> logReader = new ArrayList<>();

        try (BufferedReader bufferedReader = Files.newBufferedReader(readPath)) {

            while ((readLine = bufferedReader.readLine()) != null) {
                logReader.add(readLine);
            }
        } catch (MalformedInputException e) {
            System.err.format("MalformedInputException: %s%n:", e);
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        return logReader;
    }
}

