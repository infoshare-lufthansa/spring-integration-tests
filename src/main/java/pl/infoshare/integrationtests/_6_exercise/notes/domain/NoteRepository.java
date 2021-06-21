package pl.infoshare.integrationtests._6_exercise.notes.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {

    @Query("select n from Note n join n.tags t where t.value in :tags")
    List<Note> findAllByTags(@Param("tags") List<String> tags);

}
