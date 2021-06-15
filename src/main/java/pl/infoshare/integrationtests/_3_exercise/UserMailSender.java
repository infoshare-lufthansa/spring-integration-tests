package pl.infoshare.integrationtests._3_exercise;

import org.springframework.stereotype.Component;

@Component
public class UserMailSender {

    public void sendMail(String email, String message) {
        System.out.println(String.format("Send message '%s' to: %s", message, email));
    }
}
