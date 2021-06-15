package pl.infoshare.integrationtests._4_exercise;

import org.springframework.stereotype.Component;
import pl.infoshare.integrationtests._4_exercise.model.DateInfo;
import pl.infoshare.integrationtests._4_exercise.names.NameDayFetchService;

@Component
public class DateInfoService {

    private final DateParser dateParser;
    private final LeapYearService leapYearService;
    private final DayOfWeekService dayOfWeekService;
    private final NameDayFetchService nameDayFetchService;

    public DateInfoService(DateParser dateParser, LeapYearService leapYearService, DayOfWeekService dayOfWeekService, NameDayFetchService nameDayFetchService) {
        this.dateParser = dateParser;
        this.leapYearService = leapYearService;
        this.dayOfWeekService = dayOfWeekService;
        this.nameDayFetchService = nameDayFetchService;
    }

    public DateInfo getDateInfo(String stringDate) {
        var date = dateParser.parse(stringDate);
        var names = nameDayFetchService.fetch(date);

        return new DateInfo(
                names,
                leapYearService.isDateInLeapYear(date),
                dayOfWeekService.getDayOfWeekFromDate(date)
        );
    }
}
