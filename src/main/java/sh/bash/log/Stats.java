package sh.bash.log;

import java.util.Collections;
import java.util.List;

/**
 * LogStats calculates Stats based on a list of integers passed into it.
 * So far it can calculate averages and percentiles. It hopes to be able to do more later.
 */
public class Stats {

    public static double calculateMean(List<Integer> range) {
        int total = 0;
        double mean = 0;
        if (!range.isEmpty()) {
            for (Integer value : range) total += value;
            mean = (double) total / range.size();
            return mean;
        }
        return mean;
    }

    public static double calculatePercentile(List<Integer> range, Integer percentile) {
        int result = 0;
        if (!range.isEmpty()) {
            Collections.sort(range);
            result = range.get(range.size() * percentile / 100);
            return result;
        }
        return result;
    }

    public static double calculateVariance(List<Integer> range) {
        double variance = 0;
        if (!range.isEmpty()) {
            double mean = calculateMean(range);
            for (Integer value : range) variance += (Math.pow(value - mean, 2))/range.size();
            return variance;
            }
        return variance;
    }

    public static double calculateStdDev(List<Integer> range) {
        double stdDev = 0;
        if (!range.isEmpty()) {
            double variance = calculateVariance(range);
            stdDev = Math.sqrt(variance);
            return stdDev;

        }
        return stdDev;
    }
}
