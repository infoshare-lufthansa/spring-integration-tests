package pl.infoshare.integrationtests._7_exercise.notes;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.infoshare.integrationtests._7_exercise.model.NoteCreateRequest;
import pl.infoshare.integrationtests._7_exercise.notes.domain.Note;
import pl.infoshare.integrationtests._7_exercise.notes.domain.NoteRepository;
import pl.infoshare.integrationtests._7_exercise.notes.domain.Tag;
import pl.infoshare.integrationtests._7_exercise.notes.domain.TagRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NoteCreateService {

    private final TagRepository tagRepository;
    private final NoteRepository noteRepository;

    public NoteCreateService(TagRepository tagRepository, NoteRepository noteRepository) {
        this.tagRepository = tagRepository;
        this.noteRepository = noteRepository;
    }

    @Transactional
    public Note createNote(NoteCreateRequest noteCreateRequest) {
        var tags = createTags(noteCreateRequest.getTags());

        var note = new Note();
        note.setTags(tags);
        note.setValue(noteCreateRequest.getValue());

        return noteRepository.save(note);
    }

    private List<Tag> createTags(List<String> tags) {
        return tags.stream()
                .map(this::getOrCreate)
                .collect(Collectors.toList());
    }

    private Tag getOrCreate(String tag) {
        var existingTag = tagRepository.findByValue(tag);

        return existingTag
                .map(Tag::incrementUsed)
                .map(tagRepository::save)
                .orElseGet(() -> tagRepository.save(Tag.newTag(tag)));
    }
}
