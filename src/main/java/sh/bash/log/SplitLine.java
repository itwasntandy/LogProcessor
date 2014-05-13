package sh.bash.log;

import com.google.common.base.Splitter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* A collection of methods associated with splitting a log line.
Most are fairly general purpose, as they are just acting upon a splitted
However splitUri applies a fixed regex pattern

   It also avoids having a mega regex of doooooom!
   Our logs have the pattern:
   "\"69.64.48.165\" \"69.64.48.165,23.62.97.142\" 10.186.169.8 [10/May/2014:16:49:56 -0500] \"POST " +
   "/ean-services/rs/hotel/v3/list HTTP/1.1\" 200 143826 493 \"python-requests/2.2.0 CPython/2.7.3 " +
   "Linux/3.2.0-58-generic\" \"-\" \"api.ean.com\" \"gzip\" 80";

   To parse this with a regex would require something like:
   logPattern = "^\\\"(\\-|\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})\\\"\\s\\\"(\\-|[\\d{1,3}\\.\\d{1,3}" +
           "\\.\\d{1,3}\\.\\d{1,3}?\\,\\s]+)\\\"\\s(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})\\s\\[(\\d{2}" +
           "\\/\\w{3}\\/\\d{4})\\:(\\d{2}\\:\\d{2}\\:\\d{2})\\s\\-\\d{4}\\]\\s\\\"(\\w{3,4})\\s(\\/\\S+\\/" +
           "\\S+\\/\\S+\\/\\S+\\/\\S+)?\\/?\\??\\S+\\s(\\w{4}\\/\\d\\.\\d)\\\"\\s(\\d{3})\\s(\\S+)\\s(\\d+)" +
           "\\s\\\"(.*)\\\"\\s\\\"(.*)\\\"\\s\\\"(\\S+)\\\"\\s\\\"(\\S+)\\\"\\s(\\d{2,3})$";

    Thank fully that can be avoided, instead with the splitter, the quote-split fields become:
    TrueClientIp,XFF,NSIP Date,Request Method + URI + PROTO,RespCode,RespSize,RespTime, +
     UserAgent,SoapAction, HostHeader,AcceptEncoding,RequestPort

     For now we only worry about the URI, RespCode, RespSize and RespTime.
*/
public class SplitLine {
    private static final Splitter QUOTE_SPLITTER = Splitter.on('"')
            .trimResults()
            .omitEmptyStrings();

    private static final Splitter WHITESPACE_SPLITTER = Splitter.on(' ')
            .trimResults()
            .omitEmptyStrings();
    private static final Splitter QUESTION_SPLITTER = Splitter.on('?')
            .trimResults()
            .omitEmptyStrings();

    private static String uriLogPattern = "^(\\w{3,4})\\s(\\/\\S+\\/\\w+)(\\?\\S+\\s|\\s)(HTTP\\/\\d\\.\\d)";
    private static Pattern splitUriPattern = Pattern.compile(uriLogPattern);

    public static List<String> splitOnQuotes(String readLine) {
        List<String> splitOnQuotes;
        splitOnQuotes = QUOTE_SPLITTER.splitToList(readLine);
        return splitOnQuotes;
    }

    public static List<String> splitOnQuestion(String readLine) {
        List<String> splitOnQuestion;
        splitOnQuestion = QUESTION_SPLITTER.splitToList(readLine);
        return splitOnQuestion;
    }

    public static List<String> splitOnWhitespace(String readLine) {
        List<String> splitOnWhitespace;
        splitOnWhitespace = WHITESPACE_SPLITTER.splitToList(readLine);
        return splitOnWhitespace;
    }

    public static Matcher splitUri(String readLine) {
        Matcher splitUriMatcher;
        splitUriMatcher = splitUriPattern.matcher(readLine);
        return splitUriMatcher;

    }
}

