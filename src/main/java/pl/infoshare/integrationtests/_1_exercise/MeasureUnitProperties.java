package pl.infoshare.integrationtests._1_exercise;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Map;

@Value
@ConstructorBinding
@ConfigurationProperties("measure")
public class MeasureUnitProperties {

    String defaultUnit;
    Map<String, BigDecimal> unitFactor;

    @PostConstruct
    public void init() {
        if (!unitFactor.containsKey(defaultUnit)) {
            throw new IllegalStateException("Default unit should be set");
        }
    }

    public BigDecimal getUnitFor(String unit) {
        return unitFactor.getOrDefault(unit, unitFactor.get(defaultUnit));
    }
}
