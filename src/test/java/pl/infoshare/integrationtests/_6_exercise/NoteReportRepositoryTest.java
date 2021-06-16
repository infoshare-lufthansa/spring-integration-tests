package pl.infoshare.integrationtests._6_exercise;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.infoshare.integrationtests._6_exercise.comments.domain.Comment;
import pl.infoshare.integrationtests._6_exercise.comments.domain.CommentRepository;
import pl.infoshare.integrationtests._6_exercise.notes.domain.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class NoteReportRepositoryTest extends DatabaseIT {

    @Autowired
    private NoteReportRepository noteReportRepository;
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private TagRepository tagRepository;

    @Test
    void should_generate_report() {
        // given
        var firstTag = new Tag();
        firstTag.setValue("first");
        firstTag.setUsed(1);
        var secondTag = new Tag();
        secondTag.setValue("second");
        secondTag.setUsed(1);

        var savedFirstTag = tagRepository.save(firstTag);
        var savedSecondTag = tagRepository.save(secondTag);

        var firstNote = new Note();
        firstNote.setTags(List.of(savedFirstTag));
        firstNote.setValue("First value");

        var secondNote = new Note();
        secondNote.setTags(List.of(savedFirstTag, savedSecondTag));
        secondNote.setValue("Second value");

        var savedFirstNote = noteRepository.save(firstNote);
        var savedSecondNote = noteRepository.save(secondNote);

        var firstComment = new Comment();
        firstComment.setValue("Comment");
        firstComment.setNote(savedFirstNote);

        commentRepository.save(firstComment);

        // when
        var result = noteReportRepository.generateReport();

        // then
        assertThat(result).hasSize(2);
        assertThat(result).anySatisfy(r -> {
            assertThat(r.getId()).isEqualTo(savedFirstNote.getId());
            assertThat(r.getFirstWord()).isEqualTo("First");
            assertThat(r.getTotalComments()).isOne();
            assertThat(r.getTotalTags()).isOne();
        });
        assertThat(result).anySatisfy(r -> {
            assertThat(r.getId()).isEqualTo(savedSecondNote.getId());
            assertThat(r.getFirstWord()).isEqualTo("Second");
            assertThat(r.getTotalComments()).isZero();
            assertThat(r.getTotalTags()).isEqualTo(2);
        });
    }
}