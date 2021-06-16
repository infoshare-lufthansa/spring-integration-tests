package pl.infoshare.integrationtests._5_exercise.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class MinDateValidator implements ConstraintValidator<MinDate, LocalDate> {

    private LocalDate minDate = LocalDate.now();

    @Override
    public void initialize(MinDate constraintAnnotation) {
        String minDateString = constraintAnnotation.value();
        if (!minDateString.isBlank()) {
            minDate = LocalDate.parse(minDateString);
        }
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        return value.isAfter(minDate);
    }
}
