package pl.infoshare.integrationtests._7_exercise;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.infoshare.integrationtests._7_exercise.comments.CommentCreateService;
import pl.infoshare.integrationtests._7_exercise.comments.CommentFetchService;
import pl.infoshare.integrationtests._7_exercise.comments.domain.Comment;
import pl.infoshare.integrationtests._7_exercise.model.CommentCreateRequest;
import pl.infoshare.integrationtests._7_exercise.model.NoteCreateRequest;
import pl.infoshare.integrationtests._7_exercise.notes.NoteCreateService;
import pl.infoshare.integrationtests._7_exercise.notes.NoteDeleteService;
import pl.infoshare.integrationtests._7_exercise.notes.NoteFetchService;
import pl.infoshare.integrationtests._7_exercise.notes.domain.Note;

import java.util.List;

@RestController
public class NoteController {

    private final NoteFetchService noteFetchService;
    private final NoteCreateService noteCreateService;
    private final NoteDeleteService noteDeleteService;
    private final CommentFetchService commentFetchService;
    private final CommentCreateService commentCreateService;

    public NoteController(NoteFetchService noteFetchService, NoteCreateService noteCreateService, NoteDeleteService noteDeleteService, CommentFetchService commentFetchService, CommentCreateService commentCreateService) {
        this.noteFetchService = noteFetchService;
        this.noteCreateService = noteCreateService;
        this.noteDeleteService = noteDeleteService;
        this.commentFetchService = commentFetchService;
        this.commentCreateService = commentCreateService;
    }

    @GetMapping("/notes")
    public List<Note> getNotes(@RequestParam(required = false) List<String> tags) {
        return noteFetchService.fetchNotes(tags);

    }

    @GetMapping("/notes/{id}")
    public Note getNote(@PathVariable Integer id) {
        return noteFetchService.fetch(id);
    }

    @GetMapping("/notes/{id}/comments")
    public List<Comment> getComments(@PathVariable Integer id) {
        return commentFetchService.fetchComments(id);
    }

    @PostMapping("/notes")
    @ResponseStatus(HttpStatus.CREATED)
    public Note createNote(@RequestBody NoteCreateRequest noteCreateRequest) {
        return noteCreateService.createNote(noteCreateRequest);
    }

    @PostMapping("/notes/{noteId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment addComment(@PathVariable Integer noteId, @RequestBody CommentCreateRequest commentCreateRequest) {
        return commentCreateService.createComment(noteId, commentCreateRequest);
    }

    @DeleteMapping("/notes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNote(@PathVariable Integer id) {
        noteDeleteService.deleteNote(id);
    }
}
