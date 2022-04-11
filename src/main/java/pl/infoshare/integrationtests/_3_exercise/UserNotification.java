package pl.infoshare.integrationtests._3_exercise;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserNotification {

    String text;
    String target;
    UserNotificationResult result;

    public static UserNotification success(String text, String target) {
        return new UserNotification(text, target, UserNotificationResult.SUCCESS);
    }

    public static UserNotification inactive(String target) {
        return new UserNotification("", target, UserNotificationResult.INACTIVE);
    }
}
