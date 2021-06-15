package pl.infoshare.integrationtests._6_exercise.employee.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {MinDateValidator.class})
@Documented
public @interface MinDate {

    String value() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String message() default "Date is incorrect";
}
