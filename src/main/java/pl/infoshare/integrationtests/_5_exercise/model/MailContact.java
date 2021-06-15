package pl.infoshare.integrationtests._5_exercise.model;

public class MailContact {

    private final Integer id;
    private final String name;
    private final String address;

    public MailContact(Integer id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public MailContact withId(Integer id) {
        return new MailContact(id, name, address);
    }
}
