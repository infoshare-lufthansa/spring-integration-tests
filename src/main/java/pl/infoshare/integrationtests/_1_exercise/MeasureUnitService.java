package pl.infoshare.integrationtests._1_exercise;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MeasureUnitService {

    private final MeasureUnitProperties measureUnitProperties;

    public MeasureUnitService(MeasureUnitProperties measureUnitProperties) {
        this.measureUnitProperties = measureUnitProperties;
    }

    public BigDecimal convertUnit(BigDecimal value, String desiredUnit) {
        var factor = measureUnitProperties.getUnitFor(desiredUnit);
        return value.multiply(factor);
    }
}
