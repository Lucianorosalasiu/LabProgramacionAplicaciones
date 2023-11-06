package utils;

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
}
