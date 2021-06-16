package pl.infoshare.integrationtests._2_exercise;

public class TranslationRequest {

    private final String value;
    private final String translatedValue;

    public TranslationRequest(String value, String translatedValue) {
        this.value = value;
        this.translatedValue = translatedValue;
    }

    public String getWord() {
        return value;
    }

    public String getTranslatedWord() {
        return translatedValue;
    }
}
