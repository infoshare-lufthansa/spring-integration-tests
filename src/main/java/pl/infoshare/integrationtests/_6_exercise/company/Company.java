package pl.infoshare.integrationtests._6_exercise.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.pl.NIP;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Company {

    private final Integer id;

    @NotBlank
    @Size(min = 3)
    private final String name;

    @NIP
    @JsonProperty("nip")
    private final String taxIdentificationNumber;

    public Company(Integer id, String name, String taxIdentificationNumber) {
        this.id = id;
        this.name = name;
        this.taxIdentificationNumber = taxIdentificationNumber;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTaxIdentificationNumber() {
        return taxIdentificationNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(id, company.id) &&
                Objects.equals(name, company.name) &&
                Objects.equals(taxIdentificationNumber, company.taxIdentificationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, taxIdentificationNumber);
    }
}
