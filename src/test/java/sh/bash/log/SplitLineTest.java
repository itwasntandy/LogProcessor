package sh.bash.log;

import junit.framework.TestCase;

import java.util.List;
import java.util.regex.Matcher;

public class SplitLineTest extends TestCase {

    public void testSplitOnQuotes() throws Exception {
        String stringWithQuotes = "\"69.64.48.165\" \"69.64.48.165,23.62.97.142\" 10.186.169.8 [10/May/2014:16:49:56 -0500] \"POST " +
                "/ean-services/rs/hotel/v3/list HTTP/1.1\" 200 143826 493 \"python-requests/2.2.0 CPython/2.7.3 " +
                "Linux/3.2.0-58-generic\" \"-\" \"api.ean.com\" \"gzip\" 80\"";
        List<String> splitOnQuotes = SplitLine.splitOnQuotes(stringWithQuotes);

        assertEquals("69.64.48.165", splitOnQuotes.get(0));
        assertEquals("POST /ean-services/rs/hotel/v3/list HTTP/1.1", splitOnQuotes.get(3));

    }

    public void testSplitOnQuestion() throws Exception {
        String stringWithQuestionMark = "/ean-services/rs/hotel/v3/list?apiKey=5478b8jfuyc9ajguq7tfck7w&sig=8af9c00df6a5c533de9d3f5459652c1a&cid=368255&minorRev=14&local=en_US&currencyCode=USD&customerIpAddress=&xml=%3C?xml%20version=%221.0%22%20encoding=%22utf-8%22?%3E%0D%0A%3CHotelListRequest%20xmlns:xsi=%22http://www.w3.org/2001/XMLSchema-instance%22%20xmlns:xsd=%22http://www.w3.org/2001/XMLSchema%22%3E%0D%0A%20%20%3CapiKey%3E5478b8jfuyc9ajguq7tfck7w%3C/apiKey%3E%0D%0A%20%20%3Ccid%3E368255%3C/cid%3E%0D%0A%20%20%3Csig%3E8af9c00df6a5c533de9d3f5459652c1a%3C/sig%3E%0D%0A%20%20%3CminorRev%3E14%3C/minorRev%3E%0D%0A%20%20%3ChotelIdList%3E119291%3C/hotelIdList%3E%0D%0A%20%20%3ChotelIdList%3E312060%3C/hotelIdList%3E%0D%0A%20%20%3ChotelIdList%3E237199%3C/hotelIdList%3E%0D%0A%20%20%3ChotelIdList%3E134149%3C/hotelIdList%3E%0D%0A%20%20%3CnumberOfResults%3E4%3C/numberOfResults%3E%0D%0A%20%20%3CmaxRatePlanCount%3E1%3C/maxRatePlanCount%3E%0D%0A%20%20%3CincludeDetails%3Etrue%3C/includeDetails%3E%0D%0A%3C/HotelListRequest%3E";
        List<String> splitOnQuestion = SplitLine.splitOnQuestion(stringWithQuestionMark);

        assertEquals("/ean-services/rs/hotel/v3/list", splitOnQuestion.get(0));
    }

    public void testSplitOnWhitespace() throws Exception {
        String stringWithSpaces = " 200 102926 473 ";

        List<String> splitOnSpaces = SplitLine.splitOnWhitespace(stringWithSpaces);

        assertEquals(200, Integer.parseInt(splitOnSpaces.get(0)));
        assertEquals(102926, Integer.parseInt(splitOnSpaces.get(1)));
        assertEquals(473, Integer.parseInt(splitOnSpaces.get(2)));

    }

    public void testSplitUri() throws Exception {
        String uriString = "GET /ean-services/rs/hotel/v3/list?apiKey=&arrivalDate=05%2F16%2F2014&cid=XXXX&currencyCode=USD&debug=false&departureDate=05%2F18%2F2014&hotelIdList=393279%2C301220%2C436148&includeDetails=true&locale=ru_RU&maxRatePlanCount=40&minorRev=22&numberOfResults=200&options=DEFAULT&room1=2&supplierCacheTolerance=MIN HTTP/1.1";

        Matcher splitUri = SplitLine.splitUri(uriString);
        if(splitUri.find())
           assertEquals("/ean-services/rs/hotel/v3/list", splitUri.group(2));



    }
}