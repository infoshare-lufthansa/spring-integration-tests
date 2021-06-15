package pl.infoshare.integrationtests._3_exercise;

public class UserMail {

    private final Integer id;
    private final String username;
    private final String email;

    public UserMail(Integer id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
