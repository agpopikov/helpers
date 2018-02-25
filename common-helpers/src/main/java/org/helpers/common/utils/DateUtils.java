package org.helpers.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 */
public class DateUtils {

    public static Date asDate(LocalDateTime value, ZoneId tz) {
        return Date.from(value.atZone(tz).toInstant());
    }

    public static Date asDate(LocalDateTime value) {
        return asDate(value, ZoneId.systemDefault());
    }

    public static Date asDate(LocalDate value, ZoneId tz) {
        return Date.from(value.atStartOfDay().atZone(tz).toInstant());
    }

    public static Date asDate(LocalDate value) {
        return asDate(value, ZoneId.systemDefault());
    }

    public static LocalDateTime asLocalDateTime(Date value, ZoneId tz) {
        return LocalDateTime.ofInstant(value.toInstant(), tz);
    }

    public static LocalDateTime asLocalDateTime(Date value) {
        return asLocalDateTime(value, ZoneId.systemDefault());
    }

    public static class Formats {
        public static final String ISO_DATE = "yyyy-MM-dd";
        public static final String ISO_DATE_TIME = "yyyy-MM-dd'T'HH:mm'Z'";
        public static final String LOCALIZED_DATE = "dd.MM.yyyy";
        public static final String LOCALIZED_DATE_TIME = "dd.MM.yyyy hh:mm:ss";
    }
}
