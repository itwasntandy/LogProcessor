package sh.bash.log;

import com.google.common.base.Joiner;
import com.google.common.collect.Multimap;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Generates CSVs from a groupFields object and outputs to a printwriter.
 */
public class CsvReport {

    public void generate(Multimap<String, LogEntry> groupFields, PrintWriter csvWriter) {
        DecimalFormat df = new DecimalFormat("##.00");
        csvWriter.println("URI,NumberOfRequests,AverageResponseTime,TP99 RespTime,RespTime StdDev,AvgRespSize");
        for (String key : groupFields.keySet()) {
            Collection<LogEntry> logEntries = groupFields.get(key);
            List<Integer> respTime = logEntries.stream().map(LogEntry::getRespTime).collect(Collectors.toList());
            List<Integer> respSize = logEntries.stream().map(LogEntry::getRespSize).collect(Collectors.toList());
            int requestCount = groupFields.get(key).size();
            double meanRespTime = Stats.calculateMean(respTime);
            double percentile99RespTime = Stats.calculatePercentile(respTime, 99);
            double stdDevRespTime = Stats.calculateStdDev(respTime);
            double meanRespSize = Stats.calculateMean(respSize);


            csvWriter.println(Joiner.on(",").join(key, requestCount,
                    df.format(meanRespTime), df.format(percentile99RespTime), df.format(stdDevRespTime), df.format(meanRespSize)));


        }
    }
}
