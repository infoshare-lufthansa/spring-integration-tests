package pl.infoshare.integrationtests._5_exercise.contact;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.infoshare.integrationtests._5_exercise.model.MailContact;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MailContactControllerTest {

    @Autowired
    private MailContactRepository mailContactRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mailContactRepository.deleteAll();
    }

    @Test
    void should_get_all_contacts() throws Exception {
        // given
        var givenFirstContact = mailContactRepository.save(new MailContact(null, "first", "first-address"));
        var givenSecondContact = mailContactRepository.save(new MailContact(null, "second", "second-address"));

        // when
        mockMvc.perform(get("/contacts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(givenFirstContact.getId()))
                .andExpect(jsonPath("$[0].name").value("first"))
                .andExpect(jsonPath("$[0].address").value("first-address"))
                .andExpect(jsonPath("$[1].id").value(givenSecondContact.getId()))
                .andExpect(jsonPath("$[1].name").value("second"))
                .andExpect(jsonPath("$[1].address").value("second-address"));
    }

    @Test
    void should_create_contacts() throws Exception {
        // given
        var givenContactToCreate = new MailContact(null, "first", "first-address");

        // when
        mockMvc.perform(post("/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(givenContactToCreate))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(notNullValue()))
                .andExpect(jsonPath("name").value("first"))
                .andExpect(jsonPath("address").value("first-address"));

        // then
        var result = mailContactRepository.findAll();
        assertThat(result).hasOnlyOneElementSatisfying(c -> {
            assertThat(c.getName()).isEqualTo("first");
            assertThat(c.getAddress()).isEqualTo("first-address");
        });
    }

    @Test
    void should_delete_contacts() throws Exception {
        // given
        var givenContact = mailContactRepository.save(new MailContact(null, "first", "first-address"));

        // when
        mockMvc.perform(delete("/contacts/{id}", givenContact.getId()))
                .andExpect(status().isNoContent());

        // then
        var result = mailContactRepository.findAll();
        assertThat(result).isEmpty();
    }
}