package edu.iit.sat.itmd4515.hanggrian.fp;

import edu.iit.sat.itmd4515.hanggrian.fp.db.Languages;
import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Language;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class LanguageTest extends DatabaseTest {
    @Test
    public void invalidPojo() {
        Language language = new Language();
        language.setName(stringSized(21));

        assertThat(validator.validate(language).stream().map(ConstraintViolation::getMessage))
            .containsExactly("size must be between 0 and 20");
    }

    @Test
    public void validPojo() {
        Language language = new Language();
        language.setName("");

        assertThat(validator.validate(language)).isEmpty();
    }

    @Test
    public void validDao() {
        assertThat(Languages.selectOneByName(entityManager, "english").getName())
            .isEqualTo("English");
    }
}
