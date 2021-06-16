package pl.infoshare.integrationtests._5_exercise;

public class CompanyWithTaxNumberAlreadyExists extends RuntimeException {

    private final String taxIdentificationNumber;

    public CompanyWithTaxNumberAlreadyExists(String taxIdentificationNumber) {
        this.taxIdentificationNumber = taxIdentificationNumber;
    }

    public String getTaxIdentificationNumber() {
        return taxIdentificationNumber;
    }
}
