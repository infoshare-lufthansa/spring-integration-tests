package pl.infoshare.integrationtests._5_exercise.contact;

import org.springframework.stereotype.Component;
import pl.infoshare.integrationtests._5_exercise.model.MailContact;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MailContactRepository {

    private final AtomicInteger idGenerator = new AtomicInteger(5);
    private final List<MailContact> contacts = new ArrayList<>();

    public List<MailContact> findAll() {
        return contacts;
    }

    public Optional<MailContact> findById(Integer id) {
        return contacts.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    public MailContact save(MailContact contact) {
        var contactWithId = contact.withId(idGenerator.getAndIncrement());
        contacts.add(contactWithId);
        return contactWithId;
    }

    public void deleteById(Integer id) {
        contacts.removeIf(c -> c.getId().equals(id));
    }

    public void deleteAll() {
        contacts.clear();
    }


}
