package pl.infoshare.integrationtests._2_exercise;

import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Napisz test integracyjny, który sprawdzi czy nowa translacja została zapisana poprawnie. Z racji tego, że repozytorium
 * nie zostało jeszcze zaimplementowane będziesz musiał je zamockować.
 */
@Component
public class TranslationService {

    private final TranslationProperties translationProperties;
    private final TranslationRepository translationRepository;

    public TranslationService(TranslationProperties translationProperties, TranslationRepository translationRepository) {
        this.translationProperties = translationProperties;
        this.translationRepository = translationRepository;
    }

    public void translate(TranslationRequest translationRequest, String language) {
        var languageToTranslate = Optional.ofNullable(language)
                .orElse(translationProperties.getDefaultLanguage());

        if (!translationRepository.existsByValueAndLanguage(translationRequest.getValue(), languageToTranslate)) {
            var translation = new Translation(translationRequest.getValue(), languageToTranslate, translationRequest.getTranslatedValue());
            translationRepository.save(translation);
        }
    }
}
