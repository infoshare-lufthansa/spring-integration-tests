package pl.infoshare.integrationtests._5_exercise.mail;

import org.springframework.stereotype.Component;

@Component
public class MailSender {

    public void sendMail(String email, String message) {
        System.out.println(String.format("Send message '%s' to: %s", message, email));
    }
}
