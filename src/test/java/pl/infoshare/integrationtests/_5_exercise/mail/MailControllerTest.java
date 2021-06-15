package pl.infoshare.integrationtests._5_exercise.mail;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.infoshare.integrationtests._5_exercise.contact.MailContactRepository;
import pl.infoshare.integrationtests._5_exercise.model.Mail;
import pl.infoshare.integrationtests._5_exercise.model.MailContact;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MailControllerTest {

    @Autowired
    private MailContactRepository mailContactRepository;
    @MockBean
    private MailSender mailSender;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mailContactRepository.deleteAll();
    }

    @Test
    void should_send_mail_notification() throws Exception {
        // given
        var givenMail = new Mail("text");
        var givenContact = mailContactRepository.save(new MailContact(null, "contact", "contact-address"));

        // when
        mockMvc.perform(post("/contacts/{id}/mail", givenContact.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(givenMail))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("result").value("SUCCESS"));

        // then
        verify(mailSender).sendMail("contact-address", "Dear contact! text");
    }

    @Test
    void should_not_send_mail_notification_when_contact_does_not_exist() throws Exception {
        // given
        var givenMail = new Mail("text");

        // when
        mockMvc.perform(post("/contacts/{id}/mail", 99)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(givenMail))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("result").value("CONTACT_NOT_FOUND"));

        // then
        verify(mailSender, never()).sendMail(isA(String.class), isA(String.class));
    }
}