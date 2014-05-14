package sh.bash.log;

import junit.framework.TestCase;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class StatsTest extends TestCase {

    public void testCalculateAverage() throws Exception {
        List<Integer> averageList = new ArrayList<>();
        averageList.add(2);
        averageList.add(4);
        averageList.add(6);
        averageList.add(8);
        averageList.add(10);
        assertEquals(6.00, Stats.calculateMean(averageList));


    }

    public void testCalculatePercentile() throws Exception {
        List<Integer> percentileList = new ArrayList<>();
        int percentile = 99;
        for (Integer counter = 0; counter <= 1000; counter++) {
            percentileList.add(counter);
        }
        assertEquals(990.0, Stats.calculatePercentile(percentileList, percentile));
    }

    public void testCalculateVariance() throws Exception {
        List<Integer> varianceList = new ArrayList<>();
        varianceList.add(2);
        varianceList.add(4);
        varianceList.add(6);
        varianceList.add(8);
        varianceList.add(10);

        assertEquals(8.0, Stats.calculateVariance(varianceList));
    }

    public void testCalculateStdDev() throws Exception {
        DecimalFormat df = new DecimalFormat("##.00");
        List<Integer> stdDevList = new ArrayList<>();
        stdDevList.add(2);
        stdDevList.add(4);
        stdDevList.add(6);
        stdDevList.add(8);
        stdDevList.add(10);

        assertEquals("2.83", df.format(Stats.calculateStdDev(stdDevList)));
    }
}