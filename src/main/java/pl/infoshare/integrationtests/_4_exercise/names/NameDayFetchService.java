package pl.infoshare.integrationtests._4_exercise.names;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class NameDayFetchService {

    private final NameDayRepository nameDayRepository;

    public NameDayFetchService(NameDayRepository nameDayRepository) {
        this.nameDayRepository = nameDayRepository;
    }

    public List<String> fetch(LocalDate date) {
        return nameDayRepository.getNameDaysForDate(date);
    }
}
