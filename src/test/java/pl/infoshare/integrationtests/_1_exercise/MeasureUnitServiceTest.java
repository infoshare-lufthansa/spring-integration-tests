package pl.infoshare.integrationtests._1_exercise;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MeasureUnitServiceTest {

    @Autowired
    private MeasureUnitService testObj;

    @ParameterizedTest
    @MethodSource("getData")
    void should_convert_units(String unit, Integer value, Double expectedValue) {
        // given
        var givenValue = BigDecimal.valueOf(value);

        // when
        var result = testObj.convertUnit(givenValue, unit);

        // then
        assertThat(result).isEqualByComparingTo(BigDecimal.valueOf(expectedValue));
    }

    @Test
    void should_fallback_to_default_unit() {
        // given
        var givenValue = BigDecimal.valueOf(10);
        var givenNotExistingUnit = "cents";

        // when
        var result = testObj.convertUnit(givenValue, givenNotExistingUnit);

        // then
        assertThat(result).isEqualByComparingTo(BigDecimal.valueOf(10));
    }

    static Stream<Arguments> getData() {
        return Stream.of(
                Arguments.of("meter", 1000, 1000.0),
                Arguments.of("kilometer", 1000, 1.0),
                Arguments.of("centimeter", 1000, 100_000.0),
                Arguments.of("feet", 1000, 3280.84)
        );
    }
}