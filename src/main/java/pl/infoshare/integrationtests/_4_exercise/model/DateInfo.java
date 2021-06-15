package pl.infoshare.integrationtests._4_exercise.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.DayOfWeek;
import java.util.List;

public class DateInfo {

    public static final String NO_NAME_DAY_MESSAGE = "No one is having a name day today";
    public static final String NAME_DAY_MESSAGE = "Today's name days: %s.";

    private final List<String> names;
    private final boolean isLeapYear;
    private final DayOfWeek dayOfWeek;

    public DateInfo(List<String> names, boolean isLeapYear, DayOfWeek dayOfWeek) {
        this.names = names;
        this.isLeapYear = isLeapYear;
        this.dayOfWeek = dayOfWeek;
    }

    @JsonProperty("message")
    public String getMessageToDisplay() {
        if (names.isEmpty()) {
            return NO_NAME_DAY_MESSAGE;
        }

        return String.format(NAME_DAY_MESSAGE, String.join(", ", names));
    }

    public boolean isLeapYear() {
        return isLeapYear;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
}
