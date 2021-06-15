package pl.infoshare.integrationtests._2_exercise;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class TranslationServiceTest {

    @Autowired
    private TranslationService translationService;
    @MockBean
    private TranslationRepository translationRepository;
    @Captor
    private ArgumentCaptor<Translation> translationCaptor;

    @Test
    void should_save_translation_for_given_language() {
        // given
        var givenRequest = new TranslationRequest("test", "test_pl");
        var givenLanguage = "pl";

        when(translationRepository.existsByValueAndLanguage("test", givenLanguage)).thenReturn(false);

        // when
        translationService.translate(givenRequest, givenLanguage);

        // then
        verify(translationRepository).save(translationCaptor.capture());

        var result = translationCaptor.getValue();
        assertThat(result.getLanguage()).isEqualTo(givenLanguage);
        assertThat(result.getValue()).isEqualTo("test");
        assertThat(result.getTranslatedValue()).isEqualTo("test_pl");
    }

    @Test
    void should_save_translation_for_default_language() {
        // given
        var givenRequest = new TranslationRequest("test", "test_en");
        String givenLanguage = null;

        when(translationRepository.existsByValueAndLanguage("test", givenLanguage)).thenReturn(false);

        // when
        translationService.translate(givenRequest, givenLanguage);

        // then
        verify(translationRepository).save(translationCaptor.capture());

        var result = translationCaptor.getValue();
        assertThat(result.getLanguage()).isEqualTo("en");
        assertThat(result.getValue()).isEqualTo("test");
        assertThat(result.getTranslatedValue()).isEqualTo("test_en");
    }

    @Test
    void should_not_save_already_existing_translation() {
        // given
        var givenRequest = new TranslationRequest("test", "test_pl");
        var givenLanguage = "pl";

        when(translationRepository.existsByValueAndLanguage("test", givenLanguage)).thenReturn(true);

        // when
        translationService.translate(givenRequest, givenLanguage);

        // then
        verify(translationRepository, never()).save(isA(Translation.class));
    }
}