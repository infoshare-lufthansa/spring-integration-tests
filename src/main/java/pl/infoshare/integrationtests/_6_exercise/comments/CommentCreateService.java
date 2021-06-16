package pl.infoshare.integrationtests._6_exercise.comments;

import org.springframework.stereotype.Component;
import pl.infoshare.integrationtests._6_exercise.EntityNotFoundException;
import pl.infoshare.integrationtests._6_exercise.comments.domain.Comment;
import pl.infoshare.integrationtests._6_exercise.comments.domain.CommentRepository;
import pl.infoshare.integrationtests._6_exercise.model.CommentCreateRequest;
import pl.infoshare.integrationtests._6_exercise.notes.domain.NoteRepository;

@Component
public class CommentCreateService {

    private final NoteRepository noteRepository;
    private final CommentRepository commentRepository;

    public CommentCreateService(NoteRepository noteRepository, CommentRepository commentRepository) {
        this.noteRepository = noteRepository;
        this.commentRepository = commentRepository;
    }

    public Comment createComment(Integer noteId, CommentCreateRequest commentCreateRequest) {
        var note = noteRepository.findById(noteId).orElseThrow(EntityNotFoundException::new);

        var newComment = new Comment();
        newComment.setNote(note);
        newComment.setValue(commentCreateRequest.getValue());

        return commentRepository.save(newComment);
    }
}
