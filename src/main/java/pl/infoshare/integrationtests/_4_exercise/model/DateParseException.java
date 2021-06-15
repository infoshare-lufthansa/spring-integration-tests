package pl.infoshare.integrationtests._4_exercise.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Cannot parse date")
public class DateParseException extends RuntimeException {
}
