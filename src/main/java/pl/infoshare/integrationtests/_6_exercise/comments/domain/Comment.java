package pl.infoshare.integrationtests._6_exercise.comments.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.infoshare.integrationtests._6_exercise.notes.domain.Note;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Integer id;

    private String value;

    @ManyToOne
    @JsonIgnore
    private Note note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }
}
