package uk.ac.nesc.idsd.action.update;

import org.junit.Assert;
import org.junit.Test;
import uk.ac.nesc.idsd.AbstractTest;
import uk.ac.nesc.idsd.model.DsdCahVisitEpisode;
import uk.ac.nesc.idsd.model.DsdCahVisitMedDetail;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by jiangj on 03/03/2015.
 */

public class EditCahModuleActionTest extends AbstractTest {

    private EditCahModuleAction editCahModuleAction = new EditCahModuleAction();

    @Test
    public void testCahVisitEpisodeParser() {
        List<String> scenarios = new ArrayList<>();
        scenarios.add("[1|Doubled|Yes|Yes|Health Professional only|Infectious Illness]");
        scenarios.add("[2|> Doubled|No|Yes|Health Professional only|Infectious Illness]");
        scenarios.add("[3|Doubled|Yes|No|Health Professional only|Infectious Illness]");

        String episodeString = buildEpisodeStringFromScenario(scenarios);
        log.info("episodeString = " + episodeString);

        Set<DsdCahVisitEpisode> episodeSet = editCahModuleAction.parseEpisodeString(episodeString);
        Assert.assertEquals("Expected episode size check", 3, episodeSet.size());

        List<DsdCahVisitEpisode> episodeList = new ArrayList<>(episodeSet);
        Collections.sort(episodeList, new Comparator<DsdCahVisitEpisode>() {
            @Override
            public int compare(DsdCahVisitEpisode o1, DsdCahVisitEpisode o2) {
                return o1.getNumberOfDays().compareTo(o2.getNumberOfDays());
            }
        });
        for (int i = 0; i < scenarios.size(); i++) {
            String scenario = scenarios.get(i);
            log.info("scenario String: " + scenario);
            String[] scenarioParameters = extractValuesFromScenarioString(scenario);
            DsdCahVisitEpisode episode = episodeList.get(i);
            log.info("episode object: " + episode);

            Assert.assertEquals("Validate number of days", scenarioParameters[0], episode.getNumberOfDays().toString());
            Assert.assertEquals("Validate oral steroids", scenarioParameters[1], episode.getOralSteroids());
            Assert.assertEquals("Validate HC injection", scenarioParameters[2], episode.getHcInjection());
            Assert.assertEquals("Validate adrenal crisis", scenarioParameters[3], episode.getAdrenalCrisis());
            Assert.assertEquals("Validate emergency attendance", scenarioParameters[4], episode.getEmergencyAttendance());
            Assert.assertEquals("Validate Predisposing condition", scenarioParameters[5], episode.getPredisposingCondition());
        }
    }

    protected String buildEpisodeStringFromScenario(List<String> scenarios) {
        StringBuilder episodeString = new StringBuilder(); //"[1, Doubled, Yes, Yes, Health Professional only];[4, > Doubled, No, Yes, Health Professional only];[6, Doubled, Yes, No, Health Professional only];";
        for (String s : scenarios) {
            episodeString.append(s + ";");
        }
        return episodeString.toString();
    }

    private String[] extractValuesFromScenarioString(String scenarioString) {
        String valuesString = editCahModuleAction.extractValuesFromBrackets(scenarioString);
        return editCahModuleAction.splitValuesToArray(valuesString);
    }

    @Test
    public void testCahVisitMedDetailParser() throws UnsupportedEncodingException {
        String originalString = "Hello man, what's your name? He said \"does it matter what my name is? \" a.a&a.!a\"a?a$a%a^a&a*a(a)asaaada{a}a[a]a@a~a'a#aMa<a>a?a,a.a/a|a\\a|a?a;a;a@'";
        String javaScriptEscaped = "Hello%20man%2C%20what%27s%20your%20name%3F%20He%20said%20%22does%20it%20matter%20what%20my%20name%20is%3F%20%22%20a.a%26a.%21a%22a%3Fa%24a%25a%5Ea%26a*a%28a%29asaaada%7Ba%7Da%5Ba%5Da@a%7Ea%27a%23aMa%3Ca%3Ea%3Fa%2Ca.a/a%7Ca%5Ca%7Ca%3Fa%3Ba%3Ba@%27";
        String medDetailString = "[Hydrocortisone|1.1|mg|06:00|];[Hydrocortisone|2.2|mcg|12:00|Test 2];[Hydrocortisone|3.3|grams|23:59|" + javaScriptEscaped + "];[||||]";

        Set<DsdCahVisitMedDetail> medDetailSet = editCahModuleAction.parseMedicineDetailString(medDetailString);
        for (DsdCahVisitMedDetail m : medDetailSet) {
            log.info("dose: " + m.getDose());
        }
        Iterator<DsdCahVisitMedDetail> iterator = medDetailSet.iterator();
        DsdCahVisitMedDetail medDetail = iterator.next();
        Assert.assertEquals("Medicine Detail 1, med name: ", "Hydrocortisone", medDetail.getMedicineName());
        Assert.assertEquals("Medicine Detail 1, dose: ", new Double(1.1), medDetail.getDose());
        Assert.assertEquals("Medicine Detail 1, unit: ", "mg", medDetail.getUnit());
        Assert.assertEquals("Medicine Detail 1, time: ", Time.valueOf("06:00:00"), medDetail.getTime());
        Assert.assertEquals("Medicine Detail 1, note: ", "", medDetail.getNote());

        medDetail = iterator.next();
        Assert.assertEquals("Medicine Detail 2, med name: ", "Hydrocortisone", medDetail.getMedicineName());
        Assert.assertEquals("Medicine Detail 2, dose: ", new Double(2.2), medDetail.getDose());
        Assert.assertEquals("Medicine Detail 2, unit: ", "mcg", medDetail.getUnit());
        Assert.assertEquals("Medicine Detail 2, time: ", Time.valueOf("12:00:00"), medDetail.getTime());
        Assert.assertEquals("Medicine Detail 2, note: ", "Test 2", medDetail.getNote());

        medDetail = iterator.next();
        Assert.assertEquals("Medicine Detail 2, med name: ", "Hydrocortisone", medDetail.getMedicineName());
        Assert.assertEquals("Medicine Detail 3, dose: ", new Double(3.3), medDetail.getDose());
        Assert.assertEquals("Medicine Detail 3, unit: ", "grams", medDetail.getUnit());
        Assert.assertEquals("Medicine Detail 3, time: ", Time.valueOf("23:59:00"), medDetail.getTime());
        Assert.assertEquals("Medicine Detail 3, note: ", originalString, medDetail.getNote());
    }

    @Test
    public void testDecodeEscapedJavaScriptString() throws UnsupportedEncodingException {
        String originalString = "Hello man, what's your name? He said \"does it matter what my name is? \" a.a&a.!a\"a?a$a%a^a&a*a(a)asaaada{a}a[a]a@a~a'a#aMa<a>a?a,a.a/a|a\\a|a?a;a;a@'";
        String javaEncodedString = URLEncoder.encode(originalString, "UTF-8");
        String javaScriptEscaped = "Hello%20man%2C%20what%27s%20your%20name%3F%20He%20said%20%22does%20it%20matter%20what%20my%20name%20is%3F%20%22%20a.a%26a.%21a%22a%3Fa%24a%25a%5Ea%26a*a%28a%29asaaada%7Ba%7Da%5Ba%5Da@a%7Ea%27a%23aMa%3Ca%3Ea%3Fa%2Ca.a/a%7Ca%5Ca%7Ca%3Fa%3Ba%3Ba@%27";

        String unescaped = URLDecoder.decode(javaScriptEscaped, "UTF-8");
        log.info("Original String: " + originalString);
        log.info("JavaScript escaped: " + javaScriptEscaped);
        log.info("Java Encoded: " + javaEncodedString);
        log.info("Decoded from JavaScript: " + unescaped);
        Assert.assertEquals("Original String V.S. Unescaped from JavaScript escaped: ", originalString, unescaped);
    }


    @Test
    public void testTimeConversion() {
        Time time = Time.valueOf("23:00:00");
        log.info("time - " + time);
        Assert.assertNotNull(time);
    }


}
