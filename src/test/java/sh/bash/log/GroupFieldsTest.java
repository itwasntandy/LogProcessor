package sh.bash.log;

import junit.framework.TestCase;


public class GroupFieldsTest extends TestCase {

    public void testAdd() throws Exception {
        String logLine = "\"69.64.48.165\" \"69.64.48.165,23.62.97.142\" 10.186.169.8 [10/May/2014:16:49:56 -0500] \"POST " +
                "/ean-services/rs/hotel/v3/list HTTP/1.1\" 200 143826 493 \"python-requests/2.2.0 CPython/2.7.3 " +
                "Linux/3.2.0-58-generic\" \"-\" \"api.ean.com\" \"gzip\" 80\"";
        GroupFields groupFields = new GroupFields();

        groupFields.add(logLine);

        assertEquals(1,groupFields.getGroupFields().size());

    }


}