package pl.infoshare.integrationtests._4_exercise;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import pl.infoshare.integrationtests._4_exercise.model.AddNamesRequest;
import pl.infoshare.integrationtests._4_exercise.names.NameDay;
import pl.infoshare.integrationtests._4_exercise.names.NameDayRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class DateInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private NameDayRepository nameDayRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        nameDayRepository.deleteAll();
    }

    @Test
    void should_fetch_date_info() throws Exception {
        // given
        var givenDate = LocalDate.of(2020, Month.NOVEMBER, 1);

        var nameDay = new NameDay();
        nameDay.setDate(givenDate);
        nameDay.setNames(List.of("Maciek", "Anastazja"));
        nameDayRepository.save(nameDay);

        // when
        mockMvc.perform(get("/date-info/{date}", givenDate))
                .andExpect(status().isOk())
                .andExpect(jsonPath("message").value("Today's name days: Maciek, Anastazja."))
                .andExpect(jsonPath("leapYear").value(true))
                .andExpect(jsonPath("dayOfWeek").value("SUNDAY"));
    }

    @Test
    void should_fetch_date_info_when_no_one_is_having_a_name_day() throws Exception {
        // given
        var givenDate = LocalDate.of(2020, Month.NOVEMBER, 1);

        // when
        mockMvc.perform(get("/date-info/{date}", givenDate))
                .andExpect(status().isOk())
                .andExpect(jsonPath("message").value("No one is having a name day today"))
                .andExpect(jsonPath("leapYear").value(true))
                .andExpect(jsonPath("dayOfWeek").value("SUNDAY"));
    }

    @Test
    void should_add_name_day_to_specific_date() throws Exception {
        // given
        var givenDate = LocalDate.of(2020, Month.NOVEMBER, 1);
        var givenAddNamesRequest = new AddNamesRequest(List.of("Maciek"));

        // when
        mockMvc.perform(post("/date-info/{date}/day-names", givenDate)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(givenAddNamesRequest))
        )
                .andExpect(status().isCreated());

        // then
        var result = nameDayRepository.findOneByDate(givenDate);
        assertThat(result).hasValueSatisfying(n -> assertThat(n.getNames()).containsOnly("Maciek"));
    }
}