package pl.infoshare.integrationtests._4_exercise;

import org.springframework.stereotype.Component;
import pl.infoshare.integrationtests._4_exercise.model.DateParseException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Component
public class DateParser {

    public LocalDate parse(String date) {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException ex) {
            throw new DateParseException();
        }
    }

}
