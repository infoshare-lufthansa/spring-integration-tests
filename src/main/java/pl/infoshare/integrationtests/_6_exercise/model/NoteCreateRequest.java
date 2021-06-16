package pl.infoshare.integrationtests._6_exercise.model;

import java.util.List;

public class NoteCreateRequest {

    private final String value;
    private final List<String> tags;

    public NoteCreateRequest(String value, List<String> tags) {
        this.value = value;
        this.tags = tags;
    }

    public String getValue() {
        return value;
    }

    public List<String> getTags() {
        return tags;
    }
}
