package pl.infoshare.integrationtests._3_exercise;

import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.stereotype.Component;

@Component
public class UserNotifierService {

    private final Environment environment;
    private final UserMailSender userMailSender;

    public UserNotifierService(Environment environment, UserMailSender userMailSender) {
        this.environment = environment;
        this.userMailSender = userMailSender;
    }

    public UserNotificationResult sendNotification(UserMail userMail) {
        if (shouldSendEmail()) {
            var message = generateMessage(userMail);
            userMailSender.sendMail(userMail.getEmail(), message);
            return UserNotificationResult.SUCCESS;
        }

        return UserNotificationResult.INACTIVE;
    }

    private boolean shouldSendEmail() {
        return environment.acceptsProfiles(Profiles.of("email"));
    }

    private String generateMessage(UserMail userMail) {
        return String.format("Dear %s! Welcome to the family.", userMail.getUsername());
    }


}
