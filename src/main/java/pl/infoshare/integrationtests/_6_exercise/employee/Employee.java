package pl.infoshare.integrationtests._6_exercise.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.infoshare.integrationtests._6_exercise.employee.validator.MinDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Employee {

    @NotNull
    private final Integer id;

    @NotBlank
    @JsonProperty("first_name")
    private final String name;

    @NotBlank
    @JsonProperty("last_name")
    private final String surname;

    @Past
    @MinDate("2010-01-01")
    private final LocalDate hiredFrom;

    @NotNull
    @DecimalMin("1000")
    private final BigDecimal salary;

    public Employee(Integer id, String name, String surname, LocalDate hiredFrom, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.hiredFrom = hiredFrom;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getHiredFrom() {
        return hiredFrom;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(surname, employee.surname) &&
                Objects.equals(hiredFrom, employee.hiredFrom) &&
                Objects.equals(salary, employee.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, hiredFrom, salary);
    }
}
