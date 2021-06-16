package pl.infoshare.integrationtests._2_exercise;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Napisz test integracyjny, który sprawdzi czy nowa translacja została zapisana poprawnie. Z racji tego, że repozytorium
 * nie zostało jeszcze zaimplementowane będziesz musiał je zamockować.
 */
@Component
public class TranslationService {

    private final String languageToTranslate;
    private final TranslationRepository translationRepository;

    public TranslationService(@Value("${translations.default}") String languageToTranslate,
                              TranslationRepository translationRepository) {
        this.languageToTranslate = languageToTranslate;
        this.translationRepository = translationRepository;
    }

    public void translate(TranslationRequest translationRequest) {
        if (!translationRepository.existsByValueAndLanguage(translationRequest.getWord(), languageToTranslate)) {
            var translation = new Translation(translationRequest.getWord(), languageToTranslate, translationRequest.getTranslatedWord());
            translationRepository.save(translation);
        } else {
            throw new TranslationAlreadyExistsException(translationRequest.getWord(), languageToTranslate);
        }
    }
}
