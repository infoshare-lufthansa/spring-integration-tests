package pl.infoshare.integrationtests._2_exercise;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@SpringBootTest("translations.default=pl")
class TranslationServiceTest {

    @Autowired
    private TranslationService translationService;
    @MockBean
    private TranslationRepository translationRepository;
    @Captor
    private ArgumentCaptor<Translation> translationCaptor;

    @Test
    void should_save_translation() {
        // given
        var givenRequest = new TranslationRequest("test", "test_pl");
        String givenLanguage = null;

        when(translationRepository.existsByValueAndLanguage("test", givenLanguage)).thenReturn(false);

        // when
        translationService.translate(givenRequest);

        // then
        verify(translationRepository).save(translationCaptor.capture());

        var result = translationCaptor.getValue();
        assertThat(result.getLanguage()).isEqualTo("pl");
        assertThat(result.getValue()).isEqualTo("test");
        assertThat(result.getTranslatedValue()).isEqualTo("test_pl");
    }

    @Test
    void should_not_save_already_existing_translation() {
        // given
        var givenRequest = new TranslationRequest("test", "test_pl");
        var givenLanguage = "pl";

        when(translationRepository.existsByValueAndLanguage("test", givenLanguage)).thenReturn(true);

        // when
        assertThatThrownBy(() -> translationService.translate(givenRequest))
                .isInstanceOf(TranslationAlreadyExistsException.class)
                .hasMessage("Translation for word test in language pl already exists");

        // then
        verify(translationRepository, never()).save(isA(Translation.class));
    }
}