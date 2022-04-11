package pl.infoshare.integrationtests._3_exercise;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserNotificationFactoryTest {

    @Nested
    @ActiveProfiles("email")
    class EmailProfile {

        @Autowired
        private UserNotificationFactory testObj;

        @Test
        void should_create_success_message() {
            // given
            var givenEmail = "maciej.koziara@example.com";
            var givenUserMail = new UserMail(12, "maciej.koziara", givenEmail);

            // when
            var result = testObj.sendNotification(givenUserMail);

            // then
            assertThat(result.getResult()).isEqualTo(UserNotificationResult.SUCCESS);
            assertThat(result.getTarget()).isEqualTo(givenEmail);
            assertThat(result.getText()).isEqualTo("Dear maciej.koziara! Welcome to the family.");
        }
    }

    @Nested
    @ActiveProfiles
    class DefaultProfile {

        @Autowired
        private UserNotificationFactory testObj;

        @Test
        void should_create_success_message() {
            // given
            var givenEmail = "maciej.koziara@example.com";
            var givenUserMail = new UserMail(12, "maciej.koziara", givenEmail);

            // when
            var result = testObj.sendNotification(givenUserMail);

            // then
            assertThat(result.getResult()).isEqualTo(UserNotificationResult.INACTIVE);
            assertThat(result.getTarget()).isEqualTo(givenEmail);
            assertThat(result.getText()).isEmpty();
        }
    }


}