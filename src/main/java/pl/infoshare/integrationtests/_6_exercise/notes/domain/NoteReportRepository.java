package pl.infoshare.integrationtests._6_exercise.notes.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoteReportRepository extends JpaRepository<Note, Integer> {

    @Query(value = "select n.id as id, count(t) as totalTags, count(c) as totalComments, split_part(n.value, ' ', 1) as firstWord from note n left join comment c on n.id = c.note_id left join note_tags nt on n.id = nt.notes_id  left join tag t on nt.tags_id = t.id group by n.id, n.value", nativeQuery = true)
    List<NoteReport> generateReport();

}
