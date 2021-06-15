package pl.infoshare.integrationtests._7_exercise.comments.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.infoshare.integrationtests._7_exercise.notes.domain.Note;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByNote(Note note);

}
