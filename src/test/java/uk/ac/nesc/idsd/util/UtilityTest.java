package uk.ac.nesc.idsd.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import uk.ac.nesc.idsd.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by jiangj on 14/03/2015.
 */
public class UtilityTest {
    private static final Log log = LogFactory.getLog(UtilityTest.class);

    @Test
    public void testFullNameRegEx() {
        Map<String, Boolean> names = new HashMap<>();
        names.put("Peter Müller", true);
        names.put("François Hollande", true);
        names.put("Patrick O'Brian", true);
        names.put("Silvana Koch -Mehrin", true);
        names.put("Mathias d'Arras", true);
        names.put("Martin Luther King, Jr.", true);
        names.put("Hector Sausage-Hausen", true);
        names.put("78123", false);
        names.put("^&*(", false);
        for (Map.Entry<String, Boolean> entry : names.entrySet()) {
            assertEquals("Validating " + entry.getKey(), entry.getKey().matches(Constants.FULL_NAME_REG_EXP), entry.getValue());
        }
    }

    @Test
    public void testTrimValue() {
        Assert.assertNull(Utility.trimValue(null));
        Assert.assertNull(Utility.trimValue(""));
        assertEquals(Utility.trimValue("abc  "), "abc");
        Double d = 0.1;
        assertEquals(Utility.trimValue(d), d);
    }

    @Test
    public void testSplitter() {
        List<Integer> integers = new ArrayList<>(1001);
        for (int i = 0; i < 1002; i++) {
            integers.add(i);
        }
        List<List<Integer>> lists = Utility.splitCollection(integers);
        assertEquals("Split a list with 1002 items into lists of 999 each, should return 2 lists: ", 2, lists.size());
    }
}
