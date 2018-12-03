package uk.ac.nesc.idsd.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Created by jiangj on 26/01/2016.
 */
public class NumberFormatTest {
    protected static final Log log = LogFactory.getLog(NumberFormatTest.class);

    @Test
    public void testLocale() {
        NumberFormat qc = NumberFormat.getInstance(Locale.CANADA_FRENCH);
        System.out.println(qc.format(1234567.89));
        NumberFormat on = NumberFormat.getInstance(Locale.CANADA);
        System.out.println(on.format(1234567.89));
    }

    @Test
    public void testParsing() {
        double d = sGetDecimalStringAnyLocaleAsDouble("50.12");
        log.info(d);
    }

    public static double sGetDecimalStringAnyLocaleAsDouble(String value) {

        if (value == null) {
            log.error("Null value!");
            return 0.0;
        }

        Locale theLocale = Locale.getDefault();
        log.info("Locale = " + theLocale);
        NumberFormat numberFormat = DecimalFormat.getInstance(theLocale);
        Number theNumber;
        try {
            theNumber = numberFormat.parse(value);
            return theNumber.doubleValue();
        } catch (ParseException e) {
            // The string value might be either 99.99 or 99,99, depending on Locale.
            // We can deal with this safely, by forcing to be a point for the decimal separator, and then using Double.valueOf ...
            //http://stackoverflow.com/questions/4323599/best-way-to-parsedouble-with-comma-as-decimal-separator
            String valueWithDot = value.replaceAll(",", ".");

            try {
                return Double.valueOf(valueWithDot);
            } catch (NumberFormatException e2) {
                // This happens if we're trying (say) to parse a string that isn't a number, as though it were a number!
                // If this happens, it should only be due to application logic problems.
                // In this case, the safest thing to do is return 0, having first fired-off a log warning.
                log.warn("Warning: Value is not a number" + value);
                return 0.0;
            }
        }
    }
}
