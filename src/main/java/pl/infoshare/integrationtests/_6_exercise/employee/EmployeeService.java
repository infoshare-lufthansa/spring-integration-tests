package pl.infoshare.integrationtests._6_exercise.employee;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class EmployeeService {

    public List<Employee> findAll(String search) {
        return Collections.emptyList();
    }

    public void update(Integer id, Employee employee) {
    }

    public void remove(Integer id) {
    }

}
