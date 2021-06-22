package pl.infoshare.integrationtests._6_exercise;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
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

    @Test
    @Sql("classpath:/create_notes.sql")
    void should_generate_report() {
        // when
        var result = noteReportRepository.generateReport();

        // then
        assertThat(result).hasSize(2);
        assertThat(result).anySatisfy(r -> {
            assertThat(r.getId()).isNotNull();
            assertThat(r.getFirstWord()).isEqualTo("First");
            assertThat(r.getTotalComments()).isOne();
            assertThat(r.getTotalTags()).isOne();
        });
        assertThat(result).anySatisfy(r -> {
            assertThat(r.getId()).isNotNull();
            assertThat(r.getFirstWord()).isEqualTo("Second");
            assertThat(r.getTotalComments()).isZero();
            assertThat(r.getTotalTags()).isEqualTo(2);
        });
    }
}