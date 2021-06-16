package pl.infoshare.integrationtests._4_exercise.names;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class NameDay {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate date;

    @ElementCollection
    private List<String> names;
}
