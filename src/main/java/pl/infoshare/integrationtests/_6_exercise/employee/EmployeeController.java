package pl.infoshare.integrationtests._6_exercise.employee;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EmployeeController {

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private final EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getEmployees(@RequestParam(defaultValue = "") String search) {
        return employeeService.findAll(search);
    }

    @PutMapping("/employees/{id}")
    public void updateEmployee(@PathVariable Integer id,
                               @RequestBody @Valid Employee employee) {
        employeeService.update(id, employee);
    }

    @DeleteMapping("/employees/{id}")
    public void removeEmployee(@PathVariable Integer id) {
        employeeService.remove(id);
    }
}
