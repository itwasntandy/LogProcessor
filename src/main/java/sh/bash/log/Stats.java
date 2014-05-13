package sh.bash.log;

import java.util.Collections;
import java.util.List;

/**
 * LogStats calculates Stats based on a list of integers passed into it.
 * So far it can calculate averages and percentiles. It hopes to be able to do more later.
 */
public class Stats {

    public static double calculateAverage(List<Integer> range) {
        int total = 0;
        if (!range.isEmpty()) {
            for (Integer value : range) total += value;
            return (double) total / range.size();
        }
        return total;
    }

    public static double calculatePercentile(List<Integer> range, Integer percentile) {
        Collections.sort(range);
        return range.get(range.size() * percentile / 100);

    }
}
