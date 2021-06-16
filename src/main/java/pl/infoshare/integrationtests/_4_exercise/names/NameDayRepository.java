package pl.infoshare.integrationtests._4_exercise.names;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

public interface NameDayRepository extends JpaRepository<NameDay, Long> {

    Optional<NameDay> findOneByDate(LocalDate date);
}
