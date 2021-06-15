package pl.infoshare.integrationtests._6_exercise;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class CompanyController {

    private static final String NIP_ALREADY_EXISTS_MESSAGE = "Company with NIP %s already exists";

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/companies")
    public List<Company> getCompanies() {
        return companyService.findAll();
    }

    @PostMapping("/companies")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Company> createCompany(@RequestBody @Valid Company company) {
        var createdCompany = companyService.create(company);
        return ResponseEntity
                .created(URI.create("http://localhost:8080/companies/" + createdCompany.getId()))
                .body(createdCompany);
    }

    @PostMapping("/companies/{id}/employees")
    public void addEmployee(@RequestBody @Valid Employee employee,
                            @PathVariable Integer id) {
        companyService.addEmployee(id, employee);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CompanyWithTaxNumberAlreadyExists.class)
    public ResponseEntity<ErrorMessage> handleException(CompanyWithTaxNumberAlreadyExists exception) {
        var errorMessage = String.format(NIP_ALREADY_EXISTS_MESSAGE, exception.getTaxIdentificationNumber());
        var error = new ErrorMessage(errorMessage);

        return ResponseEntity.badRequest().body(error);
    }
}
