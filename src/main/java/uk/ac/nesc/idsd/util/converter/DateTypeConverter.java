package uk.ac.nesc.idsd.util.converter;

import org.apache.log4j.Logger;
import org.apache.struts2.util.StrutsTypeConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class DateTypeConverter extends StrutsTypeConverter {
    private static final Logger log = Logger.getLogger(DateTypeConverter.class);

    private static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
    private static final DateFormat[] ACCEPT_DATE_FORMATS = {
            new SimpleDateFormat(DEFAULT_DATE_FORMAT),
            new SimpleDateFormat("yyyy-MM-dd"),
            new SimpleDateFormat("yy-MM-dd"),
            new SimpleDateFormat("yyyy/MM/dd"),
            new SimpleDateFormat("yy/MM/dd"),
            new SimpleDateFormat("MM/dd/yyyy"),
            new SimpleDateFormat("MM/dd/yy"),
            new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy"),
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")}; //E.G. Sat Aug 01 00:00:00 BST 2009

    @SuppressWarnings("rawtypes")
    @Override
    public Object convertFromString(Map context, String[] values, Class toClass) {
        if (values[0] == null || values[0].trim().equals("")) {
            return null;
        }
        for (DateFormat format : ACCEPT_DATE_FORMATS) {
            try {
                return format.parse(values[0]);
            } catch (ParseException e) {
                log.error("Parse Exception", e);
            } catch (RuntimeException e) {
                log.error("RunTimeException", e);
            }
        }
        log.debug("can not format date string:" + values[0]);
        return null;

    }

    @SuppressWarnings("rawtypes")
    @Override
    public String convertToString(Map context, Object o) {
        //log.error("we use the date type converter, from date to string");
        if (o instanceof Date) {
            SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
            try {
                return format.format((Date) o);
            } catch (RuntimeException e) {
                log.error("Error while formatting date using default date format: " + DEFAULT_DATE_FORMAT, e);
                return "";
            }
        }
        return "";

    }

}
