package pl.infoshare.integrationtests._7_exercise.notes.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Integer> {

    Optional<Tag> findByValue(String value);

}
