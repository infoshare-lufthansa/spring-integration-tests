package pl.infoshare.integrationtests._6_exercise.company;

import org.springframework.stereotype.Component;
import pl.infoshare.integrationtests._6_exercise.employee.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class CompanyService {

    private final static AtomicInteger idProvider = new AtomicInteger();

    private final List<Company> companies = new ArrayList<>();

    public List<Company> findAll() {
        return Collections.emptyList();
    }

    public Optional<Company> findById(Integer id) {
        return Optional.empty();
    }

    public Company create(Company company) {
        return null;
    }

    public void addEmployee(Integer id, Employee employee) {
    }

    public void removeAll() {
        companies.clear();
    }

    public void removeEmployeeFromCompany(Integer companyId, Integer employeeId) {
    }
}
