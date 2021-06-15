package pl.infoshare.integrationtests._4_exercise.names;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class NameDayRepository {

    private final Map<LocalDate, List<String>> dateInfo = new HashMap<>();

    public List<String> getNameDaysForDate(LocalDate date) {
        return dateInfo.getOrDefault(date, Collections.emptyList());
    }

    public void addToDate(LocalDate date, List<String> names) {
        dateInfo.putIfAbsent(date, new ArrayList<>());
        dateInfo.get(date).addAll(names);
    }

    public void deleteAll() {
        dateInfo.clear();
    }
}
