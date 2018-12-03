package uk.ac.nesc.idsd.util.converter;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.util.StrutsTypeConverter;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

public class TimeTypeConverter extends StrutsTypeConverter {
    private static final Logger log = Logger.getLogger(TimeTypeConverter.class);

    private static final String DEFAULT_TIME_FORMAT = "HH24:mm";
    private static final DateFormat[] ACCEPT_TIME_FORMATS = {
            new SimpleDateFormat(DEFAULT_TIME_FORMAT),
            new SimpleDateFormat("HH:mm:ss zzz"),
            new SimpleDateFormat("HH:mm:ss.SSS")}; //E.G. Sat Aug 01 00:00:00 BST 2009

    @SuppressWarnings("rawtypes")
    @Override
    public Object convertFromString(Map context, String[] values, Class toClass) {
        if (values[0] == null || values[0].trim().equals("")) {
            return null;
        }
        return parseValueToTime(values[0]);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public String convertToString(Map context, Object o) {
        return o.toString();
    }

    protected Time parseValueToTime(String value) {
        Time time = Time.valueOf("00:00:00");
        if (StringUtils.isNotEmpty(value) && StringUtils.isNotEmpty(value.trim())) {
            String pattern = "([0]\\d|[1]\\d|[2][0-3]):[0-5]\\d";
            if (value.matches(pattern)) {
                value += ":00";
            }
            time = Time.valueOf(value);
        }
        return time;
    }

}
