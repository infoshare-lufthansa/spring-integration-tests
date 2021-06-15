package pl.infoshare.integrationtests._4_exercise.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AddNamesRequest {

    private final List<String> names;

    @JsonCreator
    public AddNamesRequest(@JsonProperty("names") List<String> names) {
        this.names = names;
    }

    public List<String> getNames() {
        return names;
    }
}
