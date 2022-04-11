package pl.infoshare.integrationtests._3_exercise;

import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.stereotype.Component;

@Component
public class UserNotificationFactory {

    private final Environment environment;

    public UserNotificationFactory(Environment environment) {
        this.environment = environment;
    }

    public UserNotification sendNotification(UserMail userMail) {
        if (shouldSendEmail()) {
            var message = generateMessage(userMail);
            return UserNotification.success(message, userMail.getEmail());
        }

        return UserNotification.inactive(userMail.getEmail());
    }

    private boolean shouldSendEmail() {
        return environment.acceptsProfiles(Profiles.of("email"));
    }

    private String generateMessage(UserMail userMail) {
        return String.format("Dear %s! Welcome to the family.", userMail.getUsername());
    }


}
