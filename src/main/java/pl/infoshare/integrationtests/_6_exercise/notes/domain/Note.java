package pl.infoshare.integrationtests._6_exercise.notes.domain;

import pl.infoshare.integrationtests._6_exercise.comments.domain.Comment;

import javax.persistence.*;
import java.util.List;

@Entity
public class Note {

    @Id
    @GeneratedValue
    private Integer id;

    private String value;

    @JoinTable
    @ManyToMany
    private List<Tag> tags;

    @OneToMany(mappedBy = "note", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<Comment> comments;

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

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
