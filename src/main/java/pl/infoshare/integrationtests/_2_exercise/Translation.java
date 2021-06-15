package pl.infoshare.integrationtests._2_exercise;

public class Translation {

    private final String value;
    private final String language;
    private final String translatedValue;

    public Translation(String value, String language, String translatedValue) {
        this.value = value;
        this.language = language;
        this.translatedValue = translatedValue;
    }

    public String getValue() {
        return value;
    }

    public String getLanguage() {
        return language;
    }

    public String getTranslatedValue() {
        return translatedValue;
    }
}
