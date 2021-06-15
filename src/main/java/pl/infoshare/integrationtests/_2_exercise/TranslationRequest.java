package pl.infoshare.integrationtests._2_exercise;

public class TranslationRequest {

    private final String value;
    private final String translatedValue;

    public TranslationRequest(String value, String translatedValue) {
        this.value = value;
        this.translatedValue = translatedValue;
    }

    public String getValue() {
        return value;
    }

    public String getTranslatedValue() {
        return translatedValue;
    }
}
