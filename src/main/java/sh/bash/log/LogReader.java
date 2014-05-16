package sh.bash.log;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

/* LogReader read lines from a file and returns it as a List.
 * This has a side effect of resulting in the whole line being
 * read into memory, which is not ideal.
 *  */

public class LogReader {

    public void read(String logPath, Consumer<String> logReader) {
        String readLine;
        Path readPath = Paths.get(logPath);

        try (BufferedReader bufferedReader = Files.newBufferedReader(readPath)) {

            while ((readLine = bufferedReader.readLine()) != null) {
                logReader.accept(readLine);
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

    }
}

