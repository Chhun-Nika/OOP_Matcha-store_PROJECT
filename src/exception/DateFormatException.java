package exception;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateFormatException extends IllegalArgumentException {
    public DateFormatException(String message) {
        super(message);
    }

    public static LocalDate validate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new DateFormatException("Invalid date format! Please use YYYY-MM-DD.");
        }
    }
}
