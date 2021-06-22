package pl.infoshare.integrationtests._6_exercise;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.infoshare.integrationtests._6_exercise.comments.CommentCreateService;
import pl.infoshare.integrationtests._6_exercise.comments.CommentFetchService;
import pl.infoshare.integrationtests._6_exercise.comments.domain.Comment;
import pl.infoshare.integrationtests._6_exercise.comments.domain.CommentRepository;
import pl.infoshare.integrationtests._6_exercise.model.CommentCreateRequest;
import pl.infoshare.integrationtests._6_exercise.model.NoteCreateRequest;
import pl.infoshare.integrationtests._6_exercise.notes.NoteCreateService;
import pl.infoshare.integrationtests._6_exercise.notes.NoteDeleteService;
import pl.infoshare.integrationtests._6_exercise.notes.NoteFetchService;
import pl.infoshare.integrationtests._6_exercise.notes.domain.Note;
import pl.infoshare.integrationtests._6_exercise.notes.domain.NoteRepository;
import pl.infoshare.integrationtests._6_exercise.notes.domain.Tag;
import pl.infoshare.integrationtests._6_exercise.notes.domain.TagRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class NoteController {

    private final NoteFetchService noteFetchService;
    private final NoteCreateService noteCreateService;
    private final NoteDeleteService noteDeleteService;
    private final CommentFetchService commentFetchService;
    private final CommentCreateService commentCreateService;
    private final NoteRepository noteRepository;
    private final TagRepository tagRepository;
    private final CommentRepository commentRepository;

    public NoteController(NoteFetchService noteFetchService, NoteCreateService noteCreateService, NoteDeleteService noteDeleteService, CommentFetchService commentFetchService, CommentCreateService commentCreateService, NoteRepository noteRepository, TagRepository tagRepository, CommentRepository commentRepository) {
        this.noteFetchService = noteFetchService;
        this.noteCreateService = noteCreateService;
        this.noteDeleteService = noteDeleteService;
        this.commentFetchService = commentFetchService;
        this.commentCreateService = commentCreateService;
        this.noteRepository = noteRepository;
        this.tagRepository = tagRepository;
        this.commentRepository = commentRepository;
    }

    @PostConstruct
    void init() {

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
