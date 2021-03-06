package sh.bash.log;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import junit.framework.TestCase;

import java.io.PrintWriter;
import java.io.StringWriter;

public class CsvReportTest extends TestCase {

    public void testGenerate() throws Exception {

        StringWriter stringWriter = new StringWriter();
            PrintWriter csvWriter = new PrintWriter(stringWriter);
            CsvReport csvReport = new CsvReport();
        Multimap<String, LogEntry> exampleField = ArrayListMultimap.create();
        String uriString="/ean-services/ws/hotel/v3";
        int respCode=200;
        int respSize=13580;
        int respTime=55;
        LogEntry logEntry = new LogEntry(respCode, respSize, respTime);
        exampleField.put(uriString,logEntry);
        csvReport.generate(exampleField,csvWriter);



        assertEquals("URI,NumberOfRequests,AverageResponseTime,TP99 RespTime,RespTime StdDev,AvgRespSize\n/ean-services/ws/hotel/v3,1,55.00,55.00,.00,13580.00\n", stringWriter.toString());



    }
}