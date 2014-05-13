package sh.bash.log;

import junit.framework.TestCase;

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
        assertEquals(6.00, Stats.calculateAverage(averageList));


    }

    public void testCalculatePercentile() throws Exception {
        List<Integer> percentileList = new ArrayList<>();
        int percentile = 99;
        for (Integer counter = 0; counter <= 1000; counter++) {
            percentileList.add(counter);
        }
        assertEquals(990.0, Stats.calculatePercentile(percentileList, percentile));


    }
}