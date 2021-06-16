package pl.infoshare.integrationtests._1_exercise;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class MeasureUnitServiceDemo {

    public static final String MESSAGE = "Measure unit demo for value %s and unit %s. Result: %s";

    private final MeasureUnitService measureUnitService;

//    @PostConstruct
    void demo() {
        var givenValue = BigDecimal.TEN;
        System.out.println(String.format(MESSAGE, givenValue, "meter", measureUnitService.convertUnit(givenValue, "meter")));
        System.out.println(String.format(MESSAGE, givenValue, "kilometer", measureUnitService.convertUnit(givenValue, "kilometer")));
        System.out.println(String.format(MESSAGE, givenValue, "centimeter", measureUnitService.convertUnit(givenValue, "centimeter")));
        System.out.println(String.format(MESSAGE, givenValue, "feet", measureUnitService.convertUnit(givenValue, "feet")));
        System.out.println(String.format(MESSAGE, givenValue, "mile", measureUnitService.convertUnit(givenValue, "mile")));
    }
}
