    package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZoneId;

/**
 *
 * @author alexis
 */
public class DateConverter {
    public static LocalDate convertToLocalDate(Date date) {
        if (date == null) {
            return null;
        }

        Instant instant = date.toInstant();
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date convertToDate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }

        ZoneId zoneId = ZoneId.systemDefault();
        return Date.from(localDate.atStartOfDay(zoneId).toInstant());
    }
    
    public static String dateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
        
        return dateFormat.format(date);
    }
    
    public static Date stringToDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        
        return formatter.parse(date);
    }
    
    public static String userDateToStringFront(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        
        return dateFormat.format(date);
    }
    
    public static String userDateToStringForm(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        return dateFormat.format(date);
    }
    
    public static Date userStringToDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        return formatter.parse(date);
    }
}
