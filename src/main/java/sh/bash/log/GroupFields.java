package sh.bash.log;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.List;
import java.util.regex.Matcher;

/**
 * Group Fields takes input a of a logline, calls some SplitLine methods to split the line up
 * Specifically it pulls out the Response Time, Response Size and Response Code for a request
 * It stores this data in a Map, which is keyed on the URI
 */
public class GroupFields {
    private Multimap<String, LogEntry> groupFields = ArrayListMultimap.create();

    public void add(String logLine) {
        int respCode, respSize, respTime;
        List<String> splitOnQuotes = SplitLine.splitOnQuotes(logLine);
        Matcher splitUriMatcher = SplitLine.splitUri(splitOnQuotes.get(3));
        if (splitUriMatcher.find()) {
            /* I probably lack enough regex foo, but our querystrings are often bonkers containing many question
                   marks. I can't always guarantee with the regex that the full query string has been stripped.
                   Using a splitter to strip it feels wrong, but produces significantly better results.
            */

            List<String> fullUri = SplitLine.splitOnQuestion((splitUriMatcher.group(2)));
            String uriString = fullUri.get(0);
            // Field4 from splitOnQuotes contains the values we care about.
            List<String> numericSplits = SplitLine.splitOnWhitespace(splitOnQuotes.get(4));

            respCode = Integer.parseInt(numericSplits.get(0));
            // Horrible workaround for tomcat logging "-" for zero byte files
            try {
                respSize = Integer.parseInt(numericSplits.get(1));
            } catch (NumberFormatException e) {
                respSize = 0;
            }
            respTime = Integer.parseInt(numericSplits.get(2));
            LogEntry logEntry = new LogEntry(respCode, respSize, respTime);
            groupFields.put(uriString, logEntry);
        }
    }

    // need to protect this
    public Multimap<String, LogEntry> getGroupFields() {
        return groupFields;
    }
}
