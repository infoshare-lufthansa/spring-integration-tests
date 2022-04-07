package pl.infoshare.integrationtests._6_exercise.notes.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Integer> {

    Optional<TagOverview> findByValue(String value);

    @Query("select t from Tag t where length(t.value) > 5")
    List<Tag> findLongTags();

}
