package utils;

import java.sql.Date;
import java.time.LocalDate;

public class DateLocalDateConverter {
    public static LocalDate convertDateToLocalDate(Date date) {
        return new Date(date.getTime()).toLocalDate();
    }

    public static Date convertLocalDateToDate(LocalDate localDate) {
        return Date.valueOf(localDate);
    }
}
