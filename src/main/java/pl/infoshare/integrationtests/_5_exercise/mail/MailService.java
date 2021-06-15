package pl.infoshare.integrationtests._5_exercise.mail;

import org.springframework.stereotype.Component;
import pl.infoshare.integrationtests._5_exercise.contact.MailContactRepository;
import pl.infoshare.integrationtests._5_exercise.model.Mail;
import pl.infoshare.integrationtests._5_exercise.model.MailContact;

/**
 * Sprawdź zachowanie tego serwisu w zależności od tego czy profil email jest aktywny.
 */
@Component
public class MailService {

    private final MailSender mailSender;
    private final MailContactRepository mailContactRepository;

    public MailService(MailSender mailSender, MailContactRepository mailContactRepository) {
        this.mailSender = mailSender;
        this.mailContactRepository = mailContactRepository;
    }

    public MailSendResult sendNotification(Integer contactId, Mail mail) {
        var contact = mailContactRepository.findById(contactId);
        if (contact.isPresent()) {
            var message = generateMessage(contact.get(), mail);
            mailSender.sendMail(contact.get().getAddress(), message);

            return MailSendResult.SUCCESS;
        }

        return MailSendResult.CONTACT_NOT_FOUND;
    }

    private String generateMessage(MailContact contact, Mail mail) {
        return String.format("Dear %s! %s", contact.getName(), mail.getText());
    }


}
