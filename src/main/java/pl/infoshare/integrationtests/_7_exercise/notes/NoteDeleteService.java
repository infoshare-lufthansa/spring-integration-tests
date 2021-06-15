package pl.infoshare.integrationtests._7_exercise.notes;

import org.springframework.stereotype.Component;
import pl.infoshare.integrationtests._7_exercise.notes.domain.NoteRepository;

@Component
public class NoteDeleteService {

    private final NoteRepository noteRepository;

    public NoteDeleteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public void deleteNote(Integer id) {
        noteRepository.deleteById(id);
    }
}
