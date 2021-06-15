package pl.infoshare.integrationtests._1_exercise;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Map;

@ConfigurationProperties("measure")
public class MeasureUnitProperties {

    private String defaultUnit;
    private Map<String, BigDecimal> unitFactor;

    public String getDefaultUnit() {
        return defaultUnit;
    }

    public void setDefaultUnit(String defaultUnit) {
        this.defaultUnit = defaultUnit;
    }

    public Map<String, BigDecimal> getUnitFactor() {
        return unitFactor;
    }

    public void setUnitFactor(Map<String, BigDecimal> unitFactor) {
        this.unitFactor = unitFactor;
    }

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
