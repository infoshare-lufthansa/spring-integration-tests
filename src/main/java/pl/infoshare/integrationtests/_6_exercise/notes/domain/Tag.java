package pl.infoshare.integrationtests._6_exercise.notes.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag {

    @Id
    @GeneratedValue
    private Integer id;

    private String value;

    private Integer used;

    @JsonIgnore
    @ManyToMany(mappedBy = "tags")
    private List<Note> notes;

    public static Tag newTag(String value) {
        var tag = new Tag();
        tag.setUsed(0);
        tag.setValue(value);
        tag.setNotes(new ArrayList<>());

        return tag;
    }

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

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }

    public Tag incrementUsed() {
        this.used++;
        return this;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public Tag setNotes(List<Note> notes) {
        this.notes = notes;
        return this;
    }
}
