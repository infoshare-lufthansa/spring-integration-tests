package pl.infoshare.integrationtests._3_exercise;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserNotificationController {

    private final UserNotificationService userNotificationService;

    @PostMapping("/notifications")
    public void sendNotification(@RequestBody UserMail userMail) {
        userNotificationService.sendNotification(userMail);
    }
}
