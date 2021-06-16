package pl.infoshare.integrationtests._5_exercise;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CompanyController.class)
class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CompanyService companyService;

    @Test
    void should_return_all_companies() throws Exception {
        // given
        var givenCompany = new Company(1, "name", "1140468790");
        when(companyService.findAll()).thenReturn(new PageImpl<>(List.of(givenCompany)));

        // when & then
        mockMvc.perform(get("/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].name").value("name"))
                .andExpect(jsonPath("$.content[0].nip").value("1140468790"));
    }

    @Test
    void should_create_company() throws Exception {
        // given
        var givenCompany = new Company(null, "name", "1140468790");
        var savedCompany = new Company(1, "name", "1140468790");
        when(companyService.create(givenCompany)).thenReturn(savedCompany);

        // when & then
        mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(givenCompany))
        )
                .andExpect(status().isCreated())
                .andExpect(header().stringValues("location", "http://localhost:8080/companies/1"))
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("name").value("name"))
                .andExpect(jsonPath("nip").value("1140468790"));
    }

    @ParameterizedTest
    @MethodSource("invalidCompanySource")
    void should_return_bad_request_when_company_is_incorrect(String givenName, String givenTaxNumber) throws Exception {
        // given
        var givenCompany = new Company(null, givenName, givenTaxNumber);

        // when & then
        mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(givenCompany))
        )
                .andExpect(status().isBadRequest());

        verify(companyService, never()).create(isA(Company.class));
    }

    @Test
    void should_return_bad_request_when_company_with_given_nip_already_exists() throws Exception {
        // given
        var givenCompany = new Company(null, "name", "1140468790");
        when(companyService.create(givenCompany)).thenThrow(new CompanyWithTaxNumberAlreadyExists("1140468790"));

        // when & then
        mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(givenCompany))
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message").value("Company with NIP 1140468790 already exists"));
    }

    @Test
    void should_add_employee_to_company() throws Exception {
        // given
        var givenCompany = new Company(1, "name", "1140468790");
        var givenEmployee = new Employee(1, "name", "surname", LocalDate.of(2020, Month.AUGUST, 25), BigDecimal.valueOf(1001));

        // when & then
        mockMvc.perform(post("/companies/{id}/employees", givenCompany.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(givenEmployee))
        )
                .andExpect(status().isOk());

        verify(companyService).addEmployee(1, givenEmployee);
    }

    @ParameterizedTest
    @MethodSource("invalidEmployeeSource")
    void should_return_bad_request_when_employee_is_incorrect(String givenName, String givenSurname, LocalDate givenDate, BigDecimal givenSalary) throws Exception {
        // given
        var givenCompany = new Company(1, "name", "1140468790");
        var givenEmployee = new Employee(1, givenName, givenSurname, givenDate, givenSalary);

        // when & then
        mockMvc.perform(post("/companies/{id}/employees", givenCompany.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(givenEmployee))
        )
                .andExpect(status().isBadRequest());

        verify(companyService, never()).addEmployee(isA(Integer.class), isA(Employee.class));
    }

    private static Stream<Arguments> invalidEmployeeSource() {
        return Stream.of(
                Arguments.of("name", "surname", LocalDate.of(2020, Month.AUGUST, 25), BigDecimal.ONE),
                Arguments.of("name", "surname", LocalDate.of(2020, Month.AUGUST, 25), null),
                Arguments.of("name", "surname", LocalDate.of(2009, Month.AUGUST, 25), BigDecimal.valueOf(1001)),
                Arguments.of("name", "surname", LocalDate.of(2199, Month.AUGUST, 25), BigDecimal.valueOf(1001)),
                Arguments.of("name", "", LocalDate.of(2020, Month.AUGUST, 25), BigDecimal.valueOf(1001)),
                Arguments.of("name", null, LocalDate.of(2020, Month.AUGUST, 25), BigDecimal.valueOf(1001)),
                Arguments.of("name", " ", LocalDate.of(2020, Month.AUGUST, 25), BigDecimal.valueOf(1001)),
                Arguments.of("", "surname", LocalDate.of(2020, Month.AUGUST, 25), BigDecimal.valueOf(1001)),
                Arguments.of(null, "surname", LocalDate.of(2020, Month.AUGUST, 25), BigDecimal.valueOf(1001)),
                Arguments.of(" ", "surname", LocalDate.of(2020, Month.AUGUST, 25), BigDecimal.valueOf(1001))
        );
    }

    private static Stream<Arguments> invalidCompanySource() {
        return Stream.of(
                Arguments.of("name", "1"),
                Arguments.of("", "1140468790"),
                Arguments.of(" ", "1140468790"),
                Arguments.of("\t", "1140468790"),
                Arguments.of("\n", "1140468790"),
                Arguments.of("1", "1140468790"),
                Arguments.of("2", "1140468790")
        );
    }
}