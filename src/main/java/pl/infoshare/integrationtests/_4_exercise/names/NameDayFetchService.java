package pl.infoshare.integrationtests._4_exercise.names;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NameDayFetchService {

    private final NameDayRepository nameDayRepository;

    public List<String> fetch(LocalDate date) {
        return nameDayRepository.findOneByDate(date)
                .map(NameDay::getNames)
                .orElse(Collections.emptyList());
    }
}
