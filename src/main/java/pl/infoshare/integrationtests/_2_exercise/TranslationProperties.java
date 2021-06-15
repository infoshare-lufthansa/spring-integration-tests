package pl.infoshare.integrationtests._2_exercise;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TranslationProperties {

    private final String defaultLanguage;

    public TranslationProperties(@Value("${translations.default}") String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    public String getDefaultLanguage() {
        return defaultLanguage;
    }
}
