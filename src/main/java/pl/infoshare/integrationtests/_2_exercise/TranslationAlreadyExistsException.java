package pl.infoshare.integrationtests._2_exercise;

public class TranslationAlreadyExistsException extends RuntimeException {
    private static final String EXCEPTION_MESSAGE = "Translation for word %s in language %s already exists";

    public TranslationAlreadyExistsException(String word, String language) {
        super(String.format(EXCEPTION_MESSAGE, word, language));
    }
}
